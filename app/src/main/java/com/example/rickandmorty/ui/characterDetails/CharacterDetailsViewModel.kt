package com.example.rickandmorty.ui.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.Character
import com.example.rickandmorty.data.Resource
import com.example.rickandmorty.data.repository.Repository


class CharacterDetailsViewModel (
    private val repository: Repository
):ViewModel() {

    fun getCharacterDetails(sendId:Int): LiveData<Resource<Character>> = repository.getCharacterDetails(sendId)
}