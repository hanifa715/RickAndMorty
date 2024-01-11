package com.example.rickandmorty.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.Character
import com.example.rickandmorty.data.Resource
import com.example.rickandmorty.data.repository.Repository

class CharactersViewModel (
    private val repository: Repository
) : ViewModel() {
    fun getCharacter(): LiveData<Resource<List<Character>>> = repository.getCharacter()
}