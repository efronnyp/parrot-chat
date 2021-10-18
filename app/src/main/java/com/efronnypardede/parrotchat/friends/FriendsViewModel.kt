package com.efronnypardede.parrotchat.friends

import androidx.lifecycle.*
import com.efronnypardede.parrotchat.data.model.db.RoomWithLastMessage
import com.efronnypardede.parrotchat.data.source.ChatFriendsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val chatFriendsRepository: ChatFriendsRepository
) : ViewModel() {
    val chatRooms: LiveData<List<RoomWithLastMessage>> = liveData {
        emit(chatFriendsRepository.getRooms())
    }
    val hasNoFriends: LiveData<Boolean> = chatRooms.map { it.isEmpty() }
}