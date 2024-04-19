package com.techmaster.bigotest.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.techmaster.bigotest.R
import com.techmaster.bigotest.data.ImagesModel
import com.techmaster.bigotest.databinding.ItemsviewBinding
import com.techmaster.bigotest.util.ImageLoaders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsAdapter: PagingDataAdapter<ImagesModel, ItemsAdapter.ItemViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemsviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding,parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.parent.context, DetailedScreen::class.java)
            intent.putExtra("itemData", item)
            holder.parent.context.startActivity(intent)
        }
    }

    inner class ItemViewHolder(private val binding: ItemsviewBinding, val parent: ViewGroup) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ImagesModel) {
            binding.author.text = item.author
           /* Picasso.get().load(item.download_url)
                .placeholder(R.drawable.baseline_image_24) // Placeholder image
                .error(R.drawable.baseline_broken_image_24) // Error image
                .into(binding.imageview)*/

            val coroutineScope = CoroutineScope(Dispatchers.IO)
            val coroutineScope2 = CoroutineScope(Dispatchers.Main)
            val imageLoader = ImageLoaders(parent.context, item.download_url) { bitmap ->

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


        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ImagesModel>() {
            override fun areItemsTheSame(oldItem: ImagesModel, newItem: ImagesModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImagesModel, newItem: ImagesModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}