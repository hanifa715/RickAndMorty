package com.example.rickandmorty.ui.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.example.rickandmorty.data.Resource
import com.example.rickandmorty.utils.showToast

abstract class BaseActivity : AppCompatActivity() {

    fun <T> LiveData<Resource<T>>.stateHandler(
        state: ((res: Resource<T>) -> Unit)? = null,
        success: (data: T) -> Unit,
    ) {
        observe(this@BaseActivity) { res ->
            state?.invoke(res)
            when (res) {
                is Resource.Error -> {
                    this@BaseActivity.showToast(res.message!!)
                }
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    if (res.data != null)
                        success(res.data)
                }
            }
        }
    }
}