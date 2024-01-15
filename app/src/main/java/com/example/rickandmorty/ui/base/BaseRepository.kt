package com.example.rickandmorty.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.rickandmorty.data.Resource
import com.example.rickandmorty.data.RickAndMortyApiService
import kotlinx.coroutines.Dispatchers

abstract class BaseRepository(private val api: RickAndMortyApiService) {

    protected fun <T>
            performRequest(apiCall: suspend () -> T):
            LiveData<Resource<T>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = apiCall.invoke()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "UNKNOWN ERROR"))
        }
    }
}