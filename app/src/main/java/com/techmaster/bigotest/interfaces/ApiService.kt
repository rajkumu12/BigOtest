package com.techmaster.bigotest.interfaces

import com.techmaster.bigotest.BuildConfig
import com.techmaster.bigotest.data.ImagesModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/list")
    suspend fun getImages(@Query("page") page: Int, @Query("limit") limit: Int): List<ImagesModel>

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL) // Replace with your base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}