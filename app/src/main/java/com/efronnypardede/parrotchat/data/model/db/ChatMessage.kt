package com.efronnypardede.parrotchat.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "messages",
    foreignKeys = [
        ForeignKey(
            entity = ChatUser::class,
            parentColumns = ["id"],
            childColumns = ["sender_id"],
            onUpdate = CASCADE,
            onDelete = CASCADE,
        ),
        ForeignKey(
            entity = ChatRoom::class,
            parentColumns = ["id"],
            childColumns = ["chat_room_id"],
            onUpdate = CASCADE,
            onDelete = CASCADE,
        ),
    ],
)
data class ChatMessage(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(
        name = "sender_id",
        index = true,
    ) val senderId: Long,
    @ColumnInfo(
        name = "chat_room_id",
        index = true,
    )
    val chatRoomId: Long,
    val content: String,
    val timestamp: Long,
)