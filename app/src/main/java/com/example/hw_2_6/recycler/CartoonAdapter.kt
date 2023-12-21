package com.example.hw_2_6.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.hw_2_6.data.CartoonModel
import com.example.hw_2_6.databinding.ItemCartoonBinding

class CartoonAdapter(
    private val onClick: (cartoonModel: CartoonModel.Result) -> Unit,
    private val list: List<CartoonModel.Result>
) :
    Adapter<CartoonAdapter.CartoonViewHolder>() {

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
        ViewHolder(binding.root) {
        fun bind(model: CartoonModel.Result) {
            Glide.with(binding.imgCharacter).load(model.image).into(binding.imgCharacter)
            binding.tvCharacterName.text = model.name
            binding.tvStatus.text = model.status
            binding.tvSpecies.text = model.species
            binding.tvLocationInfo.text = model.location.name
            itemView.setOnClickListener { onClick(model) }
        }
    }

}