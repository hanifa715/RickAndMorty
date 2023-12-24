package com.example.rickandmorty.data

import retrofit2.Call
import retrofit2.http.GET

interface RickAndMortyApi {
    @GET("character")
    fun getCharacter(): Call<RickAndMortyModel>
}