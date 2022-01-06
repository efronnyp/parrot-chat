package com.efronnypardede.parrotchat.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalMessageDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteMessageDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EchoWebSocketRequest

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalChatFriendsDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteChatFriendsDataSource