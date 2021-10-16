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
            entity = ChatFriend::class,
            parentColumns = ["id"],
            childColumns = ["chat_friends_id"],
            onUpdate = CASCADE,
            onDelete = CASCADE,
        ),
    ],
)
data class ChatMessage(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(
        name = "chat_friends_id",
        index = true,
    )
    val chatFriendsId: Long,
    val content: String,
    val timestamp: Long,
)