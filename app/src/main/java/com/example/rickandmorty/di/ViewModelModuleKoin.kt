package com.example.rickandmorty.di


import com.example.rickandmorty.ui.characterDetails.CharacterDetailsViewModel
import com.example.rickandmorty.ui.characters.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        CharactersViewModel(get())
    }

    viewModel{
        CharacterDetailsViewModel(get())
    }
}