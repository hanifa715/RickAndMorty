package com.example.rickandmorty.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.rickandmorty.data.Character
import com.example.rickandmorty.data.Resource
import com.example.rickandmorty.data.RickAndMortyApiService
import kotlinx.coroutines.Dispatchers

class Repository(private val api: RickAndMortyApiService) {

    fun getCharacter(): LiveData<Resource<List<Character>>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = api.getCharacter()
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let {
                    emit(Resource.Success(it.results))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
        }
    }

    fun getCharacterDetails(id: Int): LiveData<Character> = liveData(Dispatchers.IO) {

        try {
            val cartoon = api.getCharacterDetails(id)
            if (cartoon.isSuccessful) {
                cartoon.body()?.let {
                    emit(it)
                }
            }
        } catch (ex: Exception) {
            Log.e("failure", "getCharacterDetails")
        }

    }

}
