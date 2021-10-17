package com.efronnypardede.parrotchat.di

import android.content.Context
import androidx.room.Room
import com.efronnypardede.parrotchat.data.source.*
import com.efronnypardede.parrotchat.data.source.local.*
import com.efronnypardede.parrotchat.data.source.remote.ChatRoomRemoteDataSource
import com.efronnypardede.parrotchat.data.source.remote.MessageRemoteDataSource
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
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

    @Provides
    @Singleton
    fun provideMessageDao(parrotChatDatabase: ParrotChatDatabase): MessageDao =
        parrotChatDatabase.messageDao()

    @Provides
    @Singleton
    fun provideChatRoomDao(parrotChatDatabase: ParrotChatDatabase): ChatRoomDao =
        parrotChatDatabase.chatRoomDao()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor())
        .build()

    @Provides
    @Singleton
    @EchoWebSocketRequest
    fun provideEchoWebSocketRequest(): Request = Request.Builder()
        .url("wss://ws.ifelse.io")
        .build()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindModule {
    @Binds
    @Singleton
    @LocalMessageDataSource
    abstract fun provideLocalMessageDataSource(implementation: MessageLocalDataSource): MessageDataSource

    @Binds
    @Singleton
    @RemoteMessageDataSource
    @ExperimentalCoroutinesApi
    abstract fun provideRemoteMessageDataSource(implementation: MessageRemoteDataSource): MessageDataSource

    @Binds
    @Singleton
    abstract fun provideMessageRepository(implementation: MessageDataRepository): MessageRepository

    @Binds
    @Singleton
    @LocalChatRoomDataSource
    abstract fun provideLocalChatRoomDataSource(implementation: ChatRoomLocalDataSource): ChatRoomDataSource

    @Binds
    @Singleton
    @RemoteChatRoomDataSource
    abstract fun provideRemoteChatRoomDataSource(implementation: ChatRoomRemoteDataSource): ChatRoomDataSource

    @Binds
    @Singleton
    abstract fun provideChatRoomRepository(implementation: ChatRoomDataRepository): ChatRoomRepository
}