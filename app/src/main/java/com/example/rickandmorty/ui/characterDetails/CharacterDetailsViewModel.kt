package com.example.rickandmorty.ui.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.Character
import com.example.rickandmorty.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {

    fun getCharacterDetails(id:Int): LiveData<Character> = repository.getCharacterDetails(id)


}