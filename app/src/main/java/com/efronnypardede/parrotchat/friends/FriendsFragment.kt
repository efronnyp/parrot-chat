package com.efronnypardede.parrotchat.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.efronnypardede.parrotchat.R
import com.efronnypardede.parrotchat.databinding.FragmentFriendsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendsFragment private constructor() : Fragment() {

    companion object {
        fun newInstance() = FriendsFragment()
    }

    private val viewModel by viewModels<FriendsViewModel>()
    private lateinit var viewDataBinding: FragmentFriendsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FragmentFriendsBinding.inflate(layoutInflater, container, false)
            .apply {
                viewModel = this@FriendsFragment.viewModel
            }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().setTitle(R.string.friends_page_title)
        setLifecycleOwner()
        setupRecyclerViewAdapter()
    }

    private fun setLifecycleOwner() {
        //View data binding lifecycle should follow view's lifecycle
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupRecyclerViewAdapter() {
        viewDataBinding.rvChatRoom.adapter = FriendsListAdapter {
            //
        }
    }
}