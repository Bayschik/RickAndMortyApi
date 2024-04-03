package com.example.hw_2_6.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.hw_2_6.R
import com.example.hw_2_6.data.Character
import com.example.hw_2_6.databinding.ItemCartoonBinding
import com.example.hw_2_6.ui.Indicator

class CartoonAdapter(
    private val onClick: (characterId: Int) -> Unit,
    private val list:MutableList<Character>
) :Adapter<CartoonViewHolder>(
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartoonViewHolder {
        return CartoonViewHolder(
            ItemCartoonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClick
        )
    }

    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: CartoonViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun addImages(newImages: List<Character>){
        val lastIndex = list.size
        list.addAll(newImages)
        notifyItemRangeInserted(lastIndex, newImages.size)
    }

    fun reloadImages(newImages: List<Character>){
        list.clear()
        list.addAll(newImages)
        notifyItemInserted(list.lastIndex)
    }


}

class CartoonViewHolder(
    private val binding: ItemCartoonBinding,
    private val onClick: (characterId: Int) -> Unit
): ViewHolder(binding.root) {
    fun bind(model: Character) = with(binding) {
        tvCharacterName.text = model.name
        tvExistence.text = model.status
        tvSpecies.text = model.species
        tvLocationInfo.text = model.location.name
        Glide.with(imgCharacter).load(model.image).into(imgCharacter)
        itemView.setOnClickListener {onClick(model.id)}

        when(tvExistence.text.toString().uppercase()){
            Indicator.ALIVE.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_alive)
            Indicator.UNKNOWN.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_unknown)
            Indicator.DEAD.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_dead)
        }
    }
}
