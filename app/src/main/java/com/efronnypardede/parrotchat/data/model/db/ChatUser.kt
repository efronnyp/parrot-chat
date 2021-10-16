package com.efronnypardede.parrotchat.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_users")
data class ChatUser(
    @PrimaryKey val id: Long,
    val name: String,
    @ColumnInfo(name = "profile_url") val profileUrl: String?,
)
