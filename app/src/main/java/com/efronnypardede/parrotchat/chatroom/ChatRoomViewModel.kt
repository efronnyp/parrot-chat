package com.efronnypardede.parrotchat.chatroom

import androidx.lifecycle.*
import com.efronnypardede.parrotchat.data.model.MessagePackage
import com.efronnypardede.parrotchat.data.model.db.ChatMessage
import com.efronnypardede.parrotchat.data.source.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatRoomViewModel @Inject constructor(
    private val messageRepository: MessageRepository,
) : ViewModel() {
    private var roomId: Long = 0L
    private var senderId: Long = 0L
    private var partnerId: Long = 0L

    val typedMessage = MutableLiveData("")

    val messages: LiveData<List<ChatMessage>> = messageRepository.getAllMessages(roomId)
        .asLiveData(viewModelScope.coroutineContext)

    fun initialize(roomId: Long, senderId: Long, partnerId: Long) {
        this.roomId = roomId
        this.senderId = senderId
        this.partnerId = partnerId
    }

    fun sendMessage() {
        val messageText = typedMessage.value?.trim()
        if (!messageText.isNullOrBlank()) {
            viewModelScope.launch {
                val messagePackage = MessagePackage(
                    senderId,
                    partnerId,
                    roomId,
                    messageText,
                    System.currentTimeMillis()
                )
                messageRepository.sendMessage(messagePackage)
            }
            typedMessage.value = "" //Clear the message
        }
    }
}