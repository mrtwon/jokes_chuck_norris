package com.mrtwon.humorcucknoris.Retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/*
* Interface with a description of methods of interaction with the server
 */
interface JokesApi {
    @GET("random/{limit}")
    fun getJokesLimit(@Path("limit") limit: Int): Call<JokesResponse>
}