package com.efronnypardede.parrotchat.friends

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow
import com.efronnypardede.parrotchat.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                replace(R.id.container, FriendsFragment.newInstance())
            }
        }
    }
}