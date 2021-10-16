package com.efronnypardede.parrotchat.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.efronnypardede.parrotchat.data.model.db.ChatRoom
import com.efronnypardede.parrotchat.data.model.db.RoomWithLastMessage

@Dao
interface ChatRoomDao {
    suspend fun getChatRooms(): List<RoomWithLastMessage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoom(vararg chatRoom: ChatRoom)

    @Insert
    suspend fun createRoom(chatRoom: ChatRoom)
}