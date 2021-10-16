package com.efronnypardede.parrotchat.data.source

import com.efronnypardede.parrotchat.data.model.db.RoomWithLastMessage
import com.efronnypardede.parrotchat.di.LocalChatRoomDataSource
import com.efronnypardede.parrotchat.di.RemoteChatRoomDataSource
import javax.inject.Inject

class ChatRoomDataRepository @Inject constructor(
    @LocalChatRoomDataSource private val localDataSource: ChatRoomDataSource,
    @RemoteChatRoomDataSource private val remoteDataSource: ChatRoomDataSource,
) : ChatRoomRepository {
    override suspend fun getRooms(): List<RoomWithLastMessage> {
        val remoteRooms = remoteDataSource.getChatRooms()
        localDataSource.insertRooms(remoteRooms.map { it.chatRoom })
        return localDataSource.getChatRooms()
    }
}