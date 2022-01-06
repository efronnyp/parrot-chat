package com.efronnypardede.parrotchat.data.source.remote

import com.efronnypardede.parrotchat.data.model.MessagePackage
import com.efronnypardede.parrotchat.data.model.db.ChatMessage
import com.efronnypardede.parrotchat.data.source.MessageDataSource
import com.efronnypardede.parrotchat.di.EchoWebSocketRequest
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import okhttp3.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MessageRemoteDataSource @Inject constructor(
    private val okHttpClient: OkHttpClient,
    @EchoWebSocketRequest
    private val webSocketRequest: Request,
    private val gson: Gson,
) : MessageDataSource {
    companion object {
        const val NORMAL_CLOSURE_STATUS_CODE = 1000
    }

    private var webSocket: WebSocket? = null

    override suspend fun insertMessages(messages: List<ChatMessage>) {
        //Not implemented
        //In the future can be export local chat
    }

    override suspend fun getMessages(roomId: Long): List<ChatMessage> {
        return emptyList() //Not implemented. In the future can be import or sync to local.
    }

    override suspend fun sendMessage(messagePackage: MessagePackage) {
        webSocket?.send(gson.toJson(messagePackage))
    }

    override fun observeMessages(roomId: Long): Flow<List<ChatMessage>> {
        return callbackFlow {
            webSocket = okHttpClient.newWebSocket(
                webSocketRequest,
                object : WebSocketListener() {
                    override fun onFailure(
                        webSocket: WebSocket,
                        t: Throwable,
                        response: Response?
                    ) = cancel(
                        message = "WebSocket failure!",
                        cause = t
                    ) //Cancel job in this Channel

                    override fun onMessage(webSocket: WebSocket, text: String) {
                        gson.runCatching {
                            fromJson(text, MessagePackage::class.java)
                        }.onSuccess {
                            offer(
                                listOf(
                                    ChatMessage(
                                        senderId = it.recipientId, //Assign recipient as sender
                                        chatRoomId = it.roomId,
                                        content = it.message,
                                        timestamp = System.currentTimeMillis()
                                    )
                                )
                            )
                        }
                    }

                    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                        close() //Close the Channel
                    }
                }
            )

            awaitClose {
                webSocket?.closeNormalClosure()
                webSocket = null
            }
        }
    }

    private fun WebSocket.closeNormalClosure(): Boolean =
        close(NORMAL_CLOSURE_STATUS_CODE, reason = null)
}