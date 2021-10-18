package com.efronnypardede.parrotchat.chatroom

import androidx.lifecycle.*
import com.efronnypardede.parrotchat.data.model.MessagePackage
import com.efronnypardede.parrotchat.data.model.db.ChatMessage
import com.efronnypardede.parrotchat.data.source.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
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

    private val fetchTrigger: MutableLiveData<Unit> = MutableLiveData()

    val messages: LiveData<List<ChatMessage>> = fetchTrigger.switchMap {
        liveData {
            emit(messageRepository.getAllMessages(roomId))
        }
    }

    fun initialize(roomId: Long, senderId: Long, partnerId: Long) {
        this.roomId = roomId
        this.senderId = senderId
        this.partnerId = partnerId
        fetchTrigger.value = Unit //Trigger load message
        subscribeNewMessage()
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
                fetchTrigger.postValue(Unit) //Refresh message list
            }
            typedMessage.value = "" //Clear the message
        }
    }

    private fun subscribeNewMessage() {
        viewModelScope.launch {
            messageRepository.observeNewMessages(roomId)
                .collect {
                    fetchTrigger.postValue(Unit)
                }
        }
    }
}