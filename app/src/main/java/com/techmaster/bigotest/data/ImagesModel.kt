package com.techmaster.bigotest.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImagesModel (

    @SerializedName("id") val id : Int,
    @SerializedName("author") val author : String,
    @SerializedName("width") val width : Int,
    @SerializedName("height") val height : Int,
    @SerializedName("url") val url : String,
    @SerializedName("download_url") val download_url : String
): Serializable