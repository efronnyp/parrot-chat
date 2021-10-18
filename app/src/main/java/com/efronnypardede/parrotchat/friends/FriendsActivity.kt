package com.efronnypardede.parrotchat.friends

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.efronnypardede.parrotchat.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
    }
}