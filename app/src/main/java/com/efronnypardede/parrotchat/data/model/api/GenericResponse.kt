package com.efronnypardede.parrotchat.data.model.api

class GenericResponse<T>(
    val code: Int,
    val message: String,
    val data: T? = null,
)
