package com.efronnypardede.parrotchat.data.source

import com.efronnypardede.parrotchat.data.model.db.RoomWithLastMessage
import com.efronnypardede.parrotchat.di.LocalChatFriendsDataSource
import com.efronnypardede.parrotchat.di.RemoteChatFriendsDataSource
import javax.inject.Inject

class ChatFriendsDataRepository @Inject constructor(
    @LocalChatFriendsDataSource private val localDataSource: ChatFriendsDataSource,
    @RemoteChatFriendsDataSource private val remoteDataSource: ChatFriendsDataSource,
) : ChatFriendsRepository {
    override suspend fun getRooms(): List<RoomWithLastMessage> {
        val remoteRooms = remoteDataSource.getChatRooms()
        localDataSource.insertRooms(remoteRooms.map { it.chatRoom })
        return localDataSource.getChatRooms()
    }
}