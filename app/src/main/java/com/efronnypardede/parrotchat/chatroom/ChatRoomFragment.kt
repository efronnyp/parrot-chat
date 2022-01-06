package com.efronnypardede.parrotchat.chatroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.efronnypardede.parrotchat.databinding.FragmentChatRoomBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatRoomFragment : Fragment() {
    private val viewModel: ChatRoomViewModel by viewModels()

    private val navArguments: ChatRoomFragmentArgs by navArgs()

    private lateinit var dataBinding: FragmentChatRoomBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentChatRoomBinding.inflate(inflater, container, false).also {
            dataBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupDataBinding()
        setupListAdapter()
    }

    private fun setupViewModel() {
        viewModel.initialize(navArguments.roomId, navArguments.userId, navArguments.partnerId)
    }

    private fun setupListAdapter() {
        dataBinding.rvMessageList.adapter = MessageListAdapter(navArguments.userId)
    }

    private fun setupDataBinding() {
        dataBinding.apply {
            viewModel = this@ChatRoomFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }
}