package com.efronnypardede.parrotchat.friends

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efronnypardede.parrotchat.data.model.db.RoomWithLastMessage

@BindingAdapter("app:items")
fun setItems(recyclerView: RecyclerView, items: List<RoomWithLastMessage>?) {
    items?.let((recyclerView.adapter as FriendsListAdapter)::submitList)
}
