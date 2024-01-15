package com.example.rickandmorty.data.repository

import androidx.lifecycle.LiveData
import com.example.rickandmorty.data.Character
import com.example.rickandmorty.data.Resource
import com.example.rickandmorty.data.RickAndMortyApiService
import com.example.rickandmorty.ui.base.BaseRepository

class Repository(private val api: RickAndMortyApiService) : BaseRepository(api) {
    fun getCharacter(): LiveData<Resource<List<Character>>> = performRequest {
        api.getCharacter().body()?.results ?: emptyList()
    }

    fun getCharacterDetails(id: Int): LiveData<Resource<Character>> = performRequest {
        api.getCharacterDetails(id).body()!!
    }
}
