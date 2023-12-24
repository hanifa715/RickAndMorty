package com.example.rickandmorty.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.RickAndMortyModel
import com.example.rickandmorty.databinding.ItemCartoonBinding
import com.example.rickandmorty.utils.loadImage

class CartoonAdapter(
    private val onClick: (cartoonModel: RickAndMortyModel.Result) -> Unit,
    private val list: List<RickAndMortyModel.Result>
) :
    RecyclerView.Adapter<CartoonAdapter.CartoonViewHolder>() {

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

    inner class CartoonViewHolder(private val binding: ItemCartoonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: RickAndMortyModel.Result) = with(binding){
            imgCharacter.loadImage(model.image)
            tvCharacterName.text = model.name
            tvStatus.text = model.status
            tvSpecies.text = model.species
            tvLocationInfo.text = model.location.name
            itemView.setOnClickListener { onClick(model) }
        }
    }

}