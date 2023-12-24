package com.example.rickandmorty.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.RickAndMortyModel
import com.example.rickandmorty.repository.Repository

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val repository: Repository):ViewModel(){
    fun getLiveCartoon():LiveData<RickAndMortyModel>{
        return repository.getData()
    }
}