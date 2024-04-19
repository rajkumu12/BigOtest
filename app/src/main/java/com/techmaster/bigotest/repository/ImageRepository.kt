package com.techmaster.bigotest.repository

import com.techmaster.bigotest.data.ImagesModel
import com.techmaster.bigotest.interfaces.ApiService

class ImageRepository(val apiService: ApiService) {
    suspend fun getImages(page: Int, limit: Int): List<ImagesModel> {
        return apiService.getImages(page, limit)
    }



}