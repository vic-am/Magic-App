package com.accenture.magicapp.data.remote

interface ApiListener<T> {

    fun onSuccess(model: T)

    fun onFailure(string: String)
}