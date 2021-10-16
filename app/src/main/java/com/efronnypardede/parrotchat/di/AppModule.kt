package com.efronnypardede.parrotchat.di

import android.content.Context
import androidx.room.Room
import com.efronnypardede.parrotchat.data.source.local.ParrotChatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ParrotChatDatabase =
        Room.databaseBuilder(
            context,
            ParrotChatDatabase::class.java,
            "parrot_chat.db"
        )
            .createFromAsset("db/parrot_init.db")
            .build()
}