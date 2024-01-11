package com.example.hw_2_6.ui.characters

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw_2_6.data.Resource
import com.example.hw_2_6.databinding.ActivityMainBinding
import com.example.hw_2_6.recycler.CartoonAdapter
import com.example.hw_2_6.ui.base.BaseActivity
import com.example.hw_2_6.ui.characterDetails.SecondActivity
import com.example.hw_2_6.ui.utils.CartoonKeys
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModel by viewModel()
    private val cartoonAdapter by lazy { CartoonAdapter(this::onClickItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupCharactersRecycler()

        viewModel.getCharacters().stateHandler(
            success = {
                cartoonAdapter.submitList(it)
            },
            state = { state ->
                binding.progressBar.isVisible = state is Resource.Loading
            }
        )
    }

    private fun setupCharactersRecycler() = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(
            this@MainActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = cartoonAdapter
    }

    private fun onClickItem(characterId: Int) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(CartoonKeys.CHARACTER_ID_ARG, characterId)
        startActivity(intent)
    }
}