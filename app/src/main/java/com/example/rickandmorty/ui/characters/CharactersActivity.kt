package com.example.rickandmorty.ui.characters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.data.Resource
import com.example.rickandmorty.databinding.ActivityCharactersBinding
import com.example.rickandmorty.ui.characterDetails.CharacterDetailsActivity
import com.example.rickandmorty.ui.characters.adapter.CharactersAdapter
import com.example.rickandmorty.utils.CartoonKeys
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharactersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharactersBinding
    private val viewModel: CharactersViewModel by viewModel()
    private val charactersAdapter by lazy { CharactersAdapter(this::onClickItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpCharactersRecycler()

        viewModel.getCharacter().observe(this) { result ->
            when (result) {
                is Resource.Error -> {
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.isVisible = false
                }

                is Resource.Loading -> {
                   binding.progressBar.isVisible = true
                }

                is Resource.Success -> {
                    charactersAdapter.submitList(result.data)
                    binding.progressBar.isVisible = false
                }
            }

        }
    }

    private fun setUpCharactersRecycler() = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(
            this@CharactersActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = charactersAdapter
    }

    private fun onClickItem(characterId: Int) {
        val intent = Intent(this, CharacterDetailsActivity::class.java)
        intent.putExtra(CartoonKeys.CHARACTER_ID_ARG, characterId)
        startActivity(intent)
    }
}