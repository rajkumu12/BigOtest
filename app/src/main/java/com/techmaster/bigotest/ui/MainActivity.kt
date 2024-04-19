package com.techmaster.bigotest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.techmaster.bigotest.data.ImagesModel
import com.techmaster.bigotest.databinding.ActivityMainBinding
import com.techmaster.bigotest.interfaces.ApiService
import com.techmaster.bigotest.repository.ImageRepository
import com.techmaster.bigotest.ui.ItemsAdapter
import com.techmaster.bigotest.vm.ItemViewModel
import com.techmaster.bigotest.vm.ItemViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: ItemViewModel by viewModels {
        ItemViewModelFactory(ImageRepository(ApiService.create()))
    }
    private val adapter=ItemsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        initRecyclerView()
        observeViewModel()
        setContentView(binding.root)
    }

    private fun initRecyclerView() {
        binding.recycler.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recycler.adapter = adapter
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.items.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}