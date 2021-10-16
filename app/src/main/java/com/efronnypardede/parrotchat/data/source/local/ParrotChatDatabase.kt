package com.efronnypardede.parrotchat.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.efronnypardede.parrotchat.data.model.db.ChatFriend
import com.efronnypardede.parrotchat.data.model.db.ChatMessage
import com.efronnypardede.parrotchat.data.model.db.RoomWithLastMessage

@Database(
    version = 1,
    entities = [
        ChatFriend::class,
        ChatMessage::class,
    ],
    views = [RoomWithLastMessage::class],
)
abstract class ParrotChatDatabase : RoomDatabase()