package com.efronnypardede.parrotchat.data.model.db

import androidx.room.DatabaseView
import androidx.room.Embedded

@DatabaseView(
    viewName = "rooms_view",
    value = """
        SELECT cr.*, m.content lastMessage, m.timestamp lastMessageTimestamp
        FROM chat_rooms cr 
        LEFT JOIN messages m ON m.chat_room_id = cr.id
        WHERE m.id = (
            SELECT id
            FROM messages
            WHERE chat_room_id = cr.id
            ORDER BY timestamp DESC
            LIMIT 1
        ) OR NOT EXISTS(SELECT 1 FROM messages WHERE chat_room_id = cr.id)
    """
)
data class RoomWithLastMessage(
    @Embedded val chatRoom: ChatRoom,
    val lastMessage: String?,
    val lastMessageTimestamp: Long?,
)
