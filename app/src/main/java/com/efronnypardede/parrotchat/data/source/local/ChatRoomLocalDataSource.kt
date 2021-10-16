package com.efronnypardede.parrotchat.data.source.local

import com.efronnypardede.parrotchat.data.model.db.ChatRoom
import com.efronnypardede.parrotchat.data.model.db.RoomWithLastMessage
import com.efronnypardede.parrotchat.data.source.ChatRoomDataSource
import javax.inject.Inject

class ChatRoomLocalDataSource @Inject constructor(
    private val chatRoomDao: ChatRoomDao,
) : ChatRoomDataSource {
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