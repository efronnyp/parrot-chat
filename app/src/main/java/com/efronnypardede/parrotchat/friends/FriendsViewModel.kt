package com.efronnypardede.parrotchat.friends

import androidx.lifecycle.*
import com.efronnypardede.parrotchat.data.model.db.RoomWithLastMessage
import com.efronnypardede.parrotchat.data.source.ChatRoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val chatRoomRepository: ChatRoomRepository
) : ViewModel() {
    val chatRooms: LiveData<List<RoomWithLastMessage>> = liveData {
        emit(chatRoomRepository.getRooms())
    }
    val hasNoFriends: LiveData<Boolean> = chatRooms.map { it.isEmpty() }
}