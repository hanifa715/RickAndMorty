package com.example.rickandmorty.di

import com.example.rickandmorty.data.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single{
        Repository(get())
    }
}