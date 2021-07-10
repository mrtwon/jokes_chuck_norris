package com.mrtwon.humorcucknoris.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InstanceApi {
    /*
    InstanceApi object for issuing singleton retrofit object
     */
    private var retrofit: Retrofit? = null
    private var jokesApi: JokesApi? = null

    fun jokesApi(): JokesApi{
        if(jokesApi == null){
            jokesApi = InstanceApi.retorift().create(JokesApi::class.java)
        }
        return jokesApi!!
    }

    fun retorift(): Retrofit{
        if(retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("http://api.icndb.com/jokes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}