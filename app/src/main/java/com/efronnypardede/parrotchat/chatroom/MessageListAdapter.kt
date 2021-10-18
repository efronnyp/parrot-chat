package com.efronnypardede.parrotchat.chatroom

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efronnypardede.parrotchat.data.model.db.ChatMessage
import com.efronnypardede.parrotchat.databinding.MessageBubbleItemBinding

class MessageListAdapter(
    private val roomOwner: Long
) : ListAdapter<ChatMessage, MessageListAdapter.MessageListViewHolder>(DIFF_UTIL) {

    class MessageListViewHolder(
        private val binding: MessageBubbleItemBinding,
        private val roomOwner: Long,
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup, roomOwner: Long): MessageListViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = MessageBubbleItemBinding.inflate(inflater, parent, false)
                return MessageListViewHolder(binding, roomOwner)
            }

        }

        fun bind(chatMessage: ChatMessage) {
            binding.apply {
                roomOwner = this@MessageListViewHolder.roomOwner
                this@apply.chatMessage = chatMessage

                val sourceParams = messageBubbleContainer.layoutParams
                messageBubbleContainer.layoutParams = RelativeLayout.LayoutParams(sourceParams)
                    .apply {
                        val gravityRule =
                            if (chatMessage.senderId == roomOwner) RelativeLayout.ALIGN_PARENT_END
                            else RelativeLayout.ALIGN_PARENT_START
                        addRule(gravityRule)
                    }
            }
        }
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<ChatMessage>() {
            override fun areItemsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
                return oldItem.content != newItem.content
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageListViewHolder {
        return MessageListViewHolder.from(parent, roomOwner)
    }

    override fun onBindViewHolder(holder: MessageListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}