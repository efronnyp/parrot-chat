package com.efronnypardede.parrotchat.data.model.db

import androidx.room.DatabaseView
import androidx.room.Embedded

@DatabaseView(
    value = """
        SELECT cf.*, m.content lastMessage, m.timestamp lastMessageTimestamp
        FROM chat_friends cf 
        INNER JOIN messages m ON m.chat_friends_id = cf.id
        WHERE m.id = (
            SELECT id
            FROM messages
            WHERE chat_friends_id = cf.id
            ORDER BY timestamp DESC
            LIMIT 1
        )
    """
)
data class RoomWithLastMessage(
    @Embedded val chatFriend: ChatFriend,
    val lastMessage: String,
    val lastMessageTimestamp: Long,
)
