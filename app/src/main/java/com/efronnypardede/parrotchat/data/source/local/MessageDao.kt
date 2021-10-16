package com.efronnypardede.parrotchat.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.efronnypardede.parrotchat.data.model.db.ChatMessage
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(vararg message: ChatMessage)

    @Query("SELECT * FROM messages WHERE chat_room_id = :roomId ORDER BY timestamp ASC")
    suspend fun getMessagesByRoomId(roomId: Long): List<ChatMessage>

    @Query("SELECT * FROM messages WHERE chat_room_id = :roomId ORDER BY timestamp ASC")
    fun observeMessagesByRoomId(roomId: Long): Flow<List<ChatMessage>>
}