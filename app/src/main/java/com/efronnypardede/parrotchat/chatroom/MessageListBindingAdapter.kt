package com.efronnypardede.parrotchat.chatroom

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efronnypardede.parrotchat.data.model.db.ChatMessage

@BindingAdapter("items")
fun setItems(recyclerView: RecyclerView, messages: List<ChatMessage>?) {
    messages?.let((recyclerView.adapter as MessageListAdapter)::submitList)
}