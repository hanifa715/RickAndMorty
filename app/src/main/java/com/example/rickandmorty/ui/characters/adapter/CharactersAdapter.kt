package com.example.rickandmorty.ui.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.Indicator
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Character
import com.example.rickandmorty.databinding.ItemCartoonBinding
import com.example.rickandmorty.utils.loadImage

class CharactersAdapter(
    private val onClick: (characterId: Int) -> Unit,
) : RecyclerView.Adapter<CharactersAdapter.CartoonViewHolder>() {
    private var list = listOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartoonViewHolder {
        return CartoonViewHolder(
            ItemCartoonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CartoonViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun submitList(list: List<Character>) {
        this.list = list
    }

    inner class CartoonViewHolder(private val binding: ItemCartoonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Character) = with(binding) {
            imgCharacter.loadImage(model.image)
            tvCharacterName.text = model.name
            tvStatus.text = model.status
            tvSpecies.text = model.species
            tvLocationInfo.text = model.location.name
            itemView.setOnClickListener { onClick(model.id) }

            when(tvStatus.text.toString().uppercase()){
                Indicator.ALIVE.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_alive)
                Indicator.UNKNOWN.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_unknown)
                Indicator.DEAD.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_dead)
            }
        }
    }

}