package com.accenture.magicapp.data.remote

class ValidationListener(receivedMessage: String = "") {

    private var status = true
    private var message = ""

    init {
        if (receivedMessage != "") {
            status = false
            message = receivedMessage
        }
    }

    fun getStatus() = status
    fun getMessage() = message
}