package com.example.rickandmorty.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {
    @GET("character")
    fun getCharacter(): Call<BaseResponse<Character>>

    @GET("character/{id}")
    fun getCharacterDetails(
        @Path("id") id: Int
    ): Call<Character>
}