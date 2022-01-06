package com.efronnypardede.parrotchat.data.source.local

import com.efronnypardede.parrotchat.data.model.MessagePackage
import com.efronnypardede.parrotchat.data.model.db.ChatMessage
import com.efronnypardede.parrotchat.data.source.MessageDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MessageLocalDataSource @Inject constructor(
    private val messageDao: MessageDao,
) : MessageDataSource {
    override suspend fun insertMessages(messages: List<ChatMessage>) {
        messageDao.insertMessage(*messages.toTypedArray())
    }

    override suspend fun getMessages(roomId: Long): List<ChatMessage> {
        return messageDao.getMessagesByRoomId(roomId)
    }

    override suspend fun sendMessage(messagePackage: MessagePackage) {
        messageDao.insertMessage(
            ChatMessage(
                senderId = messagePackage.senderId,
                chatRoomId = messagePackage.roomId,
                content = messagePackage.message,
                timestamp = messagePackage.timestamp,
            )
        )
    }

    override fun observeMessages(roomId: Long): Flow<List<ChatMessage>> {
        return messageDao.observeMessagesByRoomId(roomId)
    }
}