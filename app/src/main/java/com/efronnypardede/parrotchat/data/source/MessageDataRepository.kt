package com.efronnypardede.parrotchat.data.source

import com.efronnypardede.parrotchat.data.model.MessagePackage
import com.efronnypardede.parrotchat.data.model.db.ChatMessage
import com.efronnypardede.parrotchat.di.LocalMessageDataSource
import com.efronnypardede.parrotchat.di.RemoteMessageDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MessageDataRepository @Inject constructor(
    @LocalMessageDataSource private val localDataSource: MessageDataSource,
    @RemoteMessageDataSource private val remoteDataSource: MessageDataSource,
) : MessageRepository {
    override suspend fun getAllMessages(roomId: Long): List<ChatMessage> {
        return localDataSource.getMessages(roomId)
    }

    override fun observeNewMessages(roomId: Long): Flow<List<ChatMessage>> {
        return remoteDataSource.observeMessages(roomId)
            .onEach(localDataSource::insertMessages)
    }

    override suspend fun sendMessage(messagePackage: MessagePackage) {
        localDataSource.sendMessage(messagePackage) //Save to local
        remoteDataSource.sendMessage(messagePackage)
    }

}