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
annotation class LocalChatRoomDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteChatRoomDataSource