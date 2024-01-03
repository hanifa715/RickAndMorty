package com.example.rickandmorty.ui.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.rickandmorty.Indicator
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Character
import com.example.rickandmorty.databinding.ItemCartoonBinding
import com.example.rickandmorty.utils.loadImage

class CharactersAdapter(
    private val onClick: (characterId: Int) -> Unit,
) : ListAdapter<Character, CartoonViewHolder>(
    RickAndMortyDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CartoonViewHolder(

        ItemCartoonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ), onClick
    )


    override fun onBindViewHolder(holder: CartoonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


class CartoonViewHolder(
    private val binding: ItemCartoonBinding,
    private val onClick: (characterId: Int) -> Unit
) : ViewHolder(binding.root) {
    fun bind(model: Character) = with(binding) {
        imgCharacter.loadImage(model.image)
        tvCharacterName.text = model.name
        tvStatus.text = model.status
        tvSpecies.text = model.species
        tvLocationInfo.text = model.location.name
        itemView.setOnClickListener { onClick(model.id) }

        when (tvStatus.text.toString().uppercase()) {
            Indicator.ALIVE.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_alive)
            Indicator.UNKNOWN.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_unknown)
            Indicator.DEAD.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_dead)
        }
    }
}

class RickAndMortyDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Character, newItem: Character) = oldItem == newItem

}