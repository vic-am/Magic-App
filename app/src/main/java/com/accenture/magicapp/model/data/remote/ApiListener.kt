package com.accenture.magicapp.model.data.remote

interface ApiListener<T> {

    fun onSuccess(model: T)

    fun onFailure(string: String)
}