package com.mrtwon.humorcucknoris.FragmentJokes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrtwon.humorcucknoris.Model.InterfaceModelApi
import com.mrtwon.humorcucknoris.Model.ModelApi
import com.mrtwon.humorcucknoris.Retrofit.JokeData
import com.mrtwon.humorcucknoris.Retrofit.JokesResponse

class JokesViewModel: ViewModel() {
    /*
    * view model of architectural components
    */

    private val modelApi: InterfaceModelApi = ModelApi()
    private val jokesListMutableLiveData =  MutableLiveData<List<JokeData>>()
    val jokesListLiveData = jokesListMutableLiveData as LiveData<List<JokeData>>
    val errorLiveData = MutableLiveData<Boolean>()

    // accessing the model to get a response from the server
    fun getJokesLimit(limit: Int){
        modelApi.getJokesLimit(limit, {
            //response
            jokesListMutableLiveData.postValue(it)
        },{
            //error
            errorLiveData.postValue(it)
        })
    }
}