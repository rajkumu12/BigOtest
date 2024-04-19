package com.techmaster.bigotest.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techmaster.bigotest.repository.ImageRepository

class ItemViewModelFactory(private val repository: ImageRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}