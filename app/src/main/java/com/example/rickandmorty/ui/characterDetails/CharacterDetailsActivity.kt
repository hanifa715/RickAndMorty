package com.example.rickandmorty.ui.characterDetails

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.rickandmorty.Indicator
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Character
import com.example.rickandmorty.data.Resource
import com.example.rickandmorty.databinding.ActivityCharacterDetailsBinding
import com.example.rickandmorty.ui.base.BaseActivity
import com.example.rickandmorty.utils.CartoonKeys
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailsActivity : BaseActivity() {
    private lateinit var binding: ActivityCharacterDetailsBinding
    private val viewModel by viewModel<CharacterDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activityId = intent.getIntExtra(CartoonKeys.CHARACTER_ID_ARG, 0)

        viewModel.getCharacterDetails(activityId).stateHandler(
            success = {
                setupCharacterData(it)
            }
        )
    }

    private fun setupCharacterData(receiveData: Character) = with(binding) {
        tvCharacterName.text = receiveData.name
        tvStatus.text = receiveData.status
        tvSpecies.text = receiveData.species
        tvLocationInfo.text = receiveData.location.name
        Glide.with(imgCharacter).load(receiveData.image).into(imgCharacter)
        when (tvStatus.text.toString().uppercase()) {
            Indicator.ALIVE.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_alive)
            Indicator.UNKNOWN.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_unknown)
            Indicator.DEAD.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_dead)
        }
    }
}