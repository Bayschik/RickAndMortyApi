package com.example.hw_2_6.ui.characterDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.hw_2_6.R
import com.example.hw_2_6.data.Character
import com.example.hw_2_6.databinding.ActivitySecondBinding
import com.example.hw_2_6.ui.Indicator
import com.example.hw_2_6.ui.utils.CartoonKeys
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private val viewModel by viewModel<CharacterDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activityId = intent.getIntExtra(CartoonKeys.CHARACTER_ID_ARG, 0)

        viewModel.getCharacterDetails(activityId).observe(this) {
            setupCharacterData(it)
        }
    }

    private fun setupCharacterData(receiveData: Character) = with(binding) {
        tvCharacterName.text = receiveData.name
        tvStatus.text = receiveData.status
        tvSpecies.text = receiveData.species
        tvLocationInfo.text = receiveData.location.name
        Glide.with(imgCharacter).load(receiveData.image).into(imgCharacter)
        when(tvStatus.text.toString().uppercase()){
            Indicator.ALIVE.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_alive)
            Indicator.UNKNOWN.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_unknown)
            Indicator.DEAD.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_dead)
        }
    }
}