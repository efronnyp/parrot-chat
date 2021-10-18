package com.efronnypardede.parrotchat.data.source.remote

import com.efronnypardede.parrotchat.data.model.db.ChatRoom
import com.efronnypardede.parrotchat.data.model.db.RoomWithLastMessage
import com.efronnypardede.parrotchat.data.source.ChatFriendsDataSource
import javax.inject.Inject

class ChatFriendsRemoteDataSource @Inject constructor() : ChatFriendsDataSource {
    override suspend fun getChatRooms(): List<RoomWithLastMessage> {
        return emptyList() //Not implemented
    }

    override suspend fun createNewRoom(newRoom: ChatRoom) {
        //Not implemented
    }

    override suspend fun insertRooms(chatRooms: List<ChatRoom>) {
        //Not implemented
    }
}