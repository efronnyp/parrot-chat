package com.efronnypardede.parrotchat.data.source

import com.efronnypardede.parrotchat.data.model.MessagePackage
import com.efronnypardede.parrotchat.data.model.db.ChatMessage
import com.efronnypardede.parrotchat.di.LocalMessageDataSource
import com.efronnypardede.parrotchat.di.RemoteMessageDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MessageDataRepository @Inject constructor(
    @LocalMessageDataSource private val localDataSource: MessageDataSource,
    @RemoteMessageDataSource private val remoteDataSource: MessageDataSource,
) : MessageRepository {
    override fun getAllMessages(roomId: Long): Flow<List<ChatMessage>> {
        return flow {
            val remoteMessages = remoteDataSource.getMessages(roomId)
            localDataSource.insertMessages(remoteMessages)
            emit(localDataSource.getMessages(roomId))
            // Observe form Web socket
            emitAll(
                remoteDataSource.observeMessages(roomId)
                    .onEach(localDataSource::insertMessages)
            )
        }
    }

    override suspend fun sendMessage(messagePackage: MessagePackage) {
        localDataSource.sendMessage(messagePackage) //Save to local
        remoteDataSource.sendMessage(messagePackage)
    }

}