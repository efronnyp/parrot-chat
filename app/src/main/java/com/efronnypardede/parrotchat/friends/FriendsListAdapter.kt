package com.efronnypardede.parrotchat.friends

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efronnypardede.parrotchat.data.model.db.ChatRoom
import com.efronnypardede.parrotchat.data.model.db.RoomWithLastMessage
import com.efronnypardede.parrotchat.databinding.ChatFriendsItemBinding

typealias ChatFriendsOnClickListener = (chatRoom: ChatRoom) -> Unit

class FriendsListAdapter(
    private val onItemClickListener: ChatFriendsOnClickListener,
) :
    ListAdapter<RoomWithLastMessage, FriendsListAdapter.FriendsViewHolder>(DIFF_UTIL) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        return FriendsViewHolder.from(parent, onItemClickListener)
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FriendsViewHolder(
        private val binding: ChatFriendsItemBinding,
        private val onClickListener: ChatFriendsOnClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(
                parent: ViewGroup,
                onClickListener: ChatFriendsOnClickListener,
            ): FriendsViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val dataBinding = ChatFriendsItemBinding.inflate(inflater, parent, false)
                return FriendsViewHolder(dataBinding, onClickListener)
            }
        }

        fun bind(roomAndMessage: RoomWithLastMessage) {
            binding.apply {
                data = roomAndMessage
                tvLastMessage.apply {
                    val alterTypeface =
                        if (roomAndMessage.lastMessage == null) Typeface.ITALIC else Typeface.NORMAL
                    setTypeface(typeface, alterTypeface)
                }
                root.setOnClickListener {
                    onClickListener(roomAndMessage.chatRoom)
                }
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<RoomWithLastMessage>() {
            override fun areItemsTheSame(
                oldItem: RoomWithLastMessage,
                newItem: RoomWithLastMessage
            ): Boolean = oldItem.chatRoom.id == newItem.chatRoom.id

            override fun areContentsTheSame(
                oldItem: RoomWithLastMessage,
                newItem: RoomWithLastMessage
            ): Boolean = oldItem.chatRoom.name == newItem.chatRoom.name &&
                    oldItem.chatRoom.imageUrl == newItem.chatRoom.imageUrl &&
                    oldItem.lastMessage == newItem.lastMessage
        }
    }
}
