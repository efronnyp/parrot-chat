package com.efronnypardede.parrotchat.data.source.local

import com.efronnypardede.parrotchat.data.model.db.ChatRoom
import com.efronnypardede.parrotchat.data.model.db.RoomWithLastMessage
import com.efronnypardede.parrotchat.data.source.ChatFriendsDataSource
import javax.inject.Inject

class ChatFriendsLocalDataSource @Inject constructor(
    private val chatRoomDao: ChatRoomDao,
) : ChatFriendsDataSource {
    override suspend fun getChatRooms(): List<RoomWithLastMessage> {
        return chatRoomDao.getChatRooms()
    }

    override suspend fun createNewRoom(newRoom: ChatRoom) {
        chatRoomDao.createRoom(newRoom)
    }

    override suspend fun insertRooms(chatRooms: List<ChatRoom>) {
        chatRoomDao.insertRoom(*chatRooms.toTypedArray())
    }
}