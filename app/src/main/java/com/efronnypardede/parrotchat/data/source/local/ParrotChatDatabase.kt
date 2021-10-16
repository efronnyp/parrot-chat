package com.efronnypardede.parrotchat.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.efronnypardede.parrotchat.data.model.db.ChatRoom
import com.efronnypardede.parrotchat.data.model.db.ChatMessage
import com.efronnypardede.parrotchat.data.model.db.ChatUser
import com.efronnypardede.parrotchat.data.model.db.RoomWithLastMessage

@Database(
    version = 1,
    entities = [
        ChatUser::class,
        ChatRoom::class,
        ChatMessage::class,
    ],
    views = [RoomWithLastMessage::class],
)
abstract class ParrotChatDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}