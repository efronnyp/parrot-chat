package com.efronnypardede.parrotchat.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class LocalMessageDataSource

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class RemoteMessageDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EchoWebSocketRequest
