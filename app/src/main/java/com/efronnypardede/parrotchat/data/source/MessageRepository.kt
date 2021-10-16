package com.efronnypardede.parrotchat.data.source

import com.efronnypardede.parrotchat.data.model.MessagePackage
import com.efronnypardede.parrotchat.data.model.db.ChatMessage
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getAllMessages(roomId: Long): Flow<List<ChatMessage>>
    suspend fun sendMessage(messagePackage: MessagePackage)
}