package com.efronnypardede.parrotchat.data.source

import com.efronnypardede.parrotchat.data.model.MessagePackage
import com.efronnypardede.parrotchat.data.model.db.ChatMessage
import kotlinx.coroutines.flow.Flow

interface MessageDataSource {
    suspend fun insertMessages(messages: List<ChatMessage>)
    suspend fun getMessages(roomId: Long): List<ChatMessage>
    suspend fun sendMessage(messagePackage: MessagePackage)
    fun observeMessages(roomId: Long): Flow<List<ChatMessage>>
}