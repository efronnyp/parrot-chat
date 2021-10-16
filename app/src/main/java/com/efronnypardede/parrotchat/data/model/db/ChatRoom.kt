package com.efronnypardede.parrotchat.data.model.db

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "chat_rooms",
    foreignKeys = [
        ForeignKey(
            entity = ChatUser::class,
            parentColumns = ["id"],
            childColumns = ["partner_id"],
            onUpdate = CASCADE,
        ),
    ],
    indices = [
        Index(value = ["partner_id"], unique = true),
    ],
)
data class ChatRoom(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "partner_id") val partnerId: Long,
    val name: String,
    @ColumnInfo(name = "created_timestamp") val createdTimestamp: Long,
    @ColumnInfo(name = "image_url") val imageUrl: String?,
)