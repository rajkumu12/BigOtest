package com.techmaster.bigotest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.techmaster.bigotest.R
import com.techmaster.bigotest.data.ImagesModel
import com.techmaster.bigotest.databinding.ActivityDetailedScreenBinding
import com.techmaster.bigotest.util.ImageLoaders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailedScreen : AppCompatActivity() {
    lateinit var data:ImagesModel
     lateinit var binding:ActivityDetailedScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailedScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            data = (intent.getSerializableExtra("itemData") as ImagesModel?)!! //Obtaining data

            binding.author.text=data.author
            val coroutineScope = CoroutineScope(Dispatchers.IO)
            val coroutineScope2 = CoroutineScope(Dispatchers.Main)
            val imageLoader = ImageLoaders(this@DetailedScreen, data.download_url) { bitmap ->

                if (bitmap != null) {
                    coroutineScope2.launch {
                        binding.imageview.setImageBitmap(bitmap)
                    }
                } else {
                    coroutineScope2.launch {
                        binding.imageview.setImageResource(R.drawable.baseline_broken_image_24)
                    }

                }
            }
            coroutineScope.launch {
                imageLoader.loadImage()
            }

            binding.imageDetail.text="Height=${data.height} ,Width=${data.width}"
            binding.imageUrl.text="Download Url=${data.download_url}"

            binding.back.setOnClickListener {
                finish()
            }

        }

    }
}