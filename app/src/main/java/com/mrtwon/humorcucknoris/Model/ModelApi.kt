package com.mrtwon.humorcucknoris.Model

import android.util.Log
import com.mrtwon.humorcucknoris.Retrofit.InstanceApi
import com.mrtwon.humorcucknoris.Retrofit.JokeData
import com.mrtwon.humorcucknoris.Retrofit.JokesResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ModelApi : InterfaceModelApi{
    /*

    * a model-level class that works with the api using retrofit
    * coroutines are used to interact with streams
    */
    private val jokesApi = InstanceApi.jokesApi()
    override fun getJokesLimit(limit: Int, callback: (List<JokeData>) -> Unit, callbackError: (Boolean) -> Unit){
        GlobalScope.launch(CoroutineExceptionHandler{ context, error ->
            callbackError(true)
            error.printStackTrace() }) {
            val response: JokesResponse = jokesApi.getJokesLimit(limit).execute().body() ?: JokesResponse()
            val isError: Boolean = if(response.value != null && response.value.isNotEmpty()){
                callback(response.value)
                false
            }else{ true }
            callbackError(isError)
        }
    }
}