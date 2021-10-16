package com.efronnypardede.parrotchat.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class LocalMessageDataSource

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class RemoteMessageDataSource

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class EchoWebSocketRequest

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class LocalChatRoomDataSource

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class RemoteChatRoomDataSource