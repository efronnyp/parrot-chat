package com.efronnypardede.parrotchat.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "chat_friends",
    indices = [
        Index(value = ["friends_name"], unique = true),
    ],
)
data class ChatFriend(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "friends_name") val friendsName: String,
    @ColumnInfo(name = "image_url") val imageUrl: String?,
)