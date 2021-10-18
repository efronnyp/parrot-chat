package com.efronnypardede.parrotchat.chatroom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efronnypardede.parrotchat.R
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
                this.chatMessage = chatMessage

                ConstraintSet().apply {
                    clone(messageBubbleRoot)
                    if (chatMessage.senderId == roomOwner) {
                        connect(
                            R.id.messageBubbleContainer,
                            ConstraintSet.END,
                            ConstraintSet.PARENT_ID,
                            ConstraintSet.END,
                        )
                        connect(
                            R.id.messageBubbleContainer,
                            ConstraintSet.START,
                            -1,
                            ConstraintSet.START,
                        )
                    } else {
                        connect(
                            R.id.messageBubbleContainer,
                            ConstraintSet.START,
                            ConstraintSet.PARENT_ID,
                            ConstraintSet.START,
                        )
                        connect(
                            R.id.messageBubbleContainer,
                            ConstraintSet.END,
                            -1,
                            ConstraintSet.END,
                        )
                    }
                    applyTo(messageBubbleRoot)
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