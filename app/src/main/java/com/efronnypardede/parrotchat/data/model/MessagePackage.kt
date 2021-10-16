package com.efronnypardede.parrotchat.data.model

data class MessagePackage(
    val senderId: Long,
    val recipientId: Long,
    val roomId: Long,
    val message: String,
    val timestamp: Long,
)
