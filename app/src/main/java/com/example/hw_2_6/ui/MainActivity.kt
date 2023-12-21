package com.example.hw_2_6.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.hw_2_6.data.CartoonModel
import com.example.hw_2_6.databinding.ActivityMainBinding
import com.example.hw_2_6.recycler.CartoonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel:ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getLiveCartoon().observe(this){
            val adapter = CartoonAdapter(this::onClickItem,it.results)
            binding.recyclerView.adapter=adapter
        }
    }
    private fun onClickItem(cartoonModel: CartoonModel.Result){
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("characterModel",cartoonModel)
        startActivity(intent)
    }
}