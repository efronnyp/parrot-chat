package com.efronnypardede.parrotchat.data.source

import com.efronnypardede.parrotchat.data.model.db.RoomWithLastMessage

interface ChatFriendsRepository {
    suspend fun getRooms(): List<RoomWithLastMessage>
}