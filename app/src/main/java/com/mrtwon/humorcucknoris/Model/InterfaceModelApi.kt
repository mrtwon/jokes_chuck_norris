package com.mrtwon.humorcucknoris.Model

import com.mrtwon.humorcucknoris.Retrofit.JokeData

interface InterfaceModelApi {
    fun getJokesLimit(limit: Int, callback: (List<JokeData>) -> Unit, callbackError: (Boolean) -> Unit)
}