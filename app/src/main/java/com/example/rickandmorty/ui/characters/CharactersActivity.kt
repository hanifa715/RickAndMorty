package com.example.rickandmorty.ui.characters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.data.Resource
import com.example.rickandmorty.databinding.ActivityCharactersBinding
import com.example.rickandmorty.ui.base.BaseActivity
import com.example.rickandmorty.ui.characterDetails.CharacterDetailsActivity
import com.example.rickandmorty.ui.characters.adapter.CharactersAdapter
import com.example.rickandmorty.utils.CartoonKeys
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharactersActivity : BaseActivity() {

    private lateinit var binding: ActivityCharactersBinding
    private val viewModel: CharactersViewModel by viewModel()
    private val charactersAdapter by lazy { CharactersAdapter(this::onClickItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpCharactersRecycler()

        viewModel.getCharacter().stateHandler(
            state = { state ->
                binding.progressBar.isVisible = state is Resource.Loading
            },
            success = {
                charactersAdapter.submitList(it)
            })
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