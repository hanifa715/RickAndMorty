package com.example.rickandmorty.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.data.RickAndMortyApiService
import com.example.rickandmorty.data.BaseResponse
import com.example.rickandmorty.data.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: RickAndMortyApiService
) {

    fun getCharacter(): MutableLiveData<List<Character>> {
        val cartoon = MutableLiveData<List<Character>>()

        api.getCharacter().enqueue(object : Callback<BaseResponse<Character>> {
            override fun onResponse(
                call: Call<BaseResponse<Character>>,
                response: Response<BaseResponse<Character>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        cartoon.postValue(it.results)
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse<Character>>, t: Throwable) {
                Log.e("ERROR", "onFailure: ${t.localizedMessage}")
            }
        })
        return cartoon
    }

    fun getCharacterDetails(id: Int): MutableLiveData<Character> {
        val cartoon = MutableLiveData<Character>()

        api.getCharacterDetails(id).enqueue(object : Callback<Character> {
            override fun onResponse(
                call: Call<Character>,
                response: Response<Character>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        cartoon.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.e("ERROR", "onFailure: ${t.localizedMessage}")
            }
        })
        return cartoon
    }
}
