package com.example.rickandmorty.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.rickandmorty.data.RickAndMortyModel
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.ui.adapter.CartoonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartoonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getLiveCartoon().observe(this) {
            val adapter = CartoonAdapter(this::onClickItem, it.results)
            binding.recyclerView.adapter = adapter
        }
    }

    private fun onClickItem(cartoonModel: RickAndMortyModel.Result) {
        val intent = Intent(this, CartoonDetailsActivity::class.java)
        intent.putExtra("characterModel", cartoonModel)
        startActivity(intent)
    }
}