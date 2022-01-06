package com.efronnypardede.parrotchat.data.source

import com.efronnypardede.parrotchat.data.model.db.ChatRoom
import com.efronnypardede.parrotchat.data.model.db.RoomWithLastMessage

interface ChatFriendsDataSource {
    suspend fun getChatRooms(): List<RoomWithLastMessage>
    suspend fun createNewRoom(newRoom: ChatRoom)
    suspend fun insertRooms(chatRooms: List<ChatRoom>)
}