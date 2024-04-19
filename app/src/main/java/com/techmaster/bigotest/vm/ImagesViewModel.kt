package com.techmaster.bigotest.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.techmaster.bigotest.data.ImagesModel
import com.techmaster.bigotest.repository.ImageRepository
import kotlinx.coroutines.flow.Flow

class ItemViewModel(private val repository: ImageRepository) : ViewModel() {

    val items: Flow<PagingData<ImagesModel>> = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        ItemPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

    companion object {
        private const val PAGE_SIZE = 20 // Adjust as per your API
    }
}