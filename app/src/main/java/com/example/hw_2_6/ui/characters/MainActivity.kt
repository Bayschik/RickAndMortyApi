package com.example.hw_2_6.ui.characters

import android.content.Intent
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private var page = 1
    private val cartoonAdapter by lazy { CartoonAdapter(this::onClickItem, mutableListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupCharactersRecycler()

        viewModel.getCharacters().stateHandler(
            success = {
                cartoonAdapter.reloadImages(it)
            },
            state = { state ->
                binding.progressBar.isVisible = state is Resource.Loading
            }
        )

        imageScroll()
    }

    private fun imageScroll() = with(binding.recyclerView) {
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = layoutManager as LinearLayoutManager
                val visibleImage = layoutManager.childCount
                val totalCount = layoutManager.itemCount
                val firstImage = layoutManager.findFirstVisibleItemPosition()

                if ((visibleImage + firstImage) >= totalCount
                    && firstImage >= 0
                    && totalCount >= PAGE_SIZE
                ) {
                    page++
                    requestImage()
                }
            }
        })
    }

    private fun requestImage() {
        viewModel.getNextPage(page).stateHandler(
            success = {
                cartoonAdapter.addImages(it)
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