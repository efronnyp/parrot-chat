package com.efronnypardede.parrotchat.friends

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efronnypardede.parrotchat.data.model.db.RoomWithLastMessage

class FriendsListAdapter :
    ListAdapter<RoomWithLastMessage, FriendsListAdapter.FriendsViewHolder>(DIFF_UTIL) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FriendsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(chatRoom: RoomWithLastMessage) {

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
            ): Boolean = oldItem.chatRoom.id == newItem.chatRoom.id &&
                    oldItem.chatRoom.name == newItem.chatRoom.name &&
                    oldItem.chatRoom.imageUrl == newItem.chatRoom.imageUrl
        }
    }
}
