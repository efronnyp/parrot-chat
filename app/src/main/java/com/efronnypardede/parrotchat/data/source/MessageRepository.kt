package com.efronnypardede.parrotchat.data.source

import com.efronnypardede.parrotchat.data.model.MessagePackage
import com.efronnypardede.parrotchat.data.model.db.ChatMessage
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    suspend fun getAllMessages(roomId: Long): List<ChatMessage>
    fun observeNewMessages(roomId: Long): Flow<List<ChatMessage>>
    suspend fun sendMessage(messagePackage: MessagePackage)
}