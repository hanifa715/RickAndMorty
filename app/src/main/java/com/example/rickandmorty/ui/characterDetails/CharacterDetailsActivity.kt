package com.example.rickandmorty.ui.characterDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.rickandmorty.Indicator
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Character
import com.example.rickandmorty.databinding.ActivityCharacterDetailsBinding
import com.example.rickandmorty.utils.CartoonKeys
import com.example.rickandmorty.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharacterDetailsBinding
    private val viewModel by viewModels<CharacterDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(CartoonKeys.CHARACTER_ID_ARG, 0)

        viewModel.getCharacterDetails(id).observe(this) {
            setUpCharacterData(it)
        }
    }

    private fun setUpCharacterData(receiveData: Character) = with(binding) {
        tvCharacterName.text = receiveData.name
        tvStatus.text = receiveData.status
        tvSpecies.text = receiveData.species
        tvLocationInfo.text = receiveData.location.name
        imgCharacter.loadImage(receiveData.image)
        when(tvStatus.text.toString().uppercase()){
            Indicator.ALIVE.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_alive)
            Indicator.UNKNOWN.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_unknown)
            Indicator.DEAD.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_dead)
        }
    }
}