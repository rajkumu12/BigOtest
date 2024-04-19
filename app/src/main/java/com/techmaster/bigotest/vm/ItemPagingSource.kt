package com.techmaster.bigotest.vm

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.techmaster.bigotest.data.ImagesModel
import com.techmaster.bigotest.repository.ImageRepository

class ItemPagingSource(private val repository: ImageRepository) : PagingSource<Int, ImagesModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImagesModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            val items = repository.getImages(nextPageNumber, 20)
            LoadResult.Page(
                data = items,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (items.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImagesModel>): Int? {
        return 1
    }
}