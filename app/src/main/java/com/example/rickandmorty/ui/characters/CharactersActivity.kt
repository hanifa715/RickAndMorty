package com.example.rickandmorty.ui.characters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.databinding.ActivityCharactersBinding
import com.example.rickandmorty.ui.characterDetails.CharacterDetailsActivity
import com.example.rickandmorty.ui.characters.adapter.CharactersAdapter
import com.example.rickandmorty.utils.CartoonKeys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharactersBinding
    private val viewModel: CharactersViewModel by viewModels()
    private val charactersAdapter by lazy { CharactersAdapter(this::onClickItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getCharacter().observe(this) {
            charactersAdapter.submitList(it)
            setUpCharactersRecycler()
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