package com.efronnypardede.parrotchat.data.model.db

import androidx.room.DatabaseView
import androidx.room.Embedded

@DatabaseView(
    value = """
        SELECT cr.*, m.content lastMessage, m.timestamp lastMessageTimestamp
        FROM chat_rooms cr 
        INNER JOIN messages m ON m.chat_room_id = cr.id
        WHERE m.id = (
            SELECT id
            FROM messages
            WHERE chat_room_id = cr.id
            ORDER BY timestamp DESC
            LIMIT 1
        )
    """
)
data class RoomWithLastMessage(
    @Embedded val chatRoom: ChatRoom,
    val lastMessage: String,
    val lastMessageTimestamp: Long,
)
