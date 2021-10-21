package com.example.parsinglocaljsonfile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parsinglocaljsonfile.databinding.ItemRowBinding

class MyAdapter(val activity: MainActivity, private val photos: ArrayList<Image>): RecyclerView.Adapter<MyAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val photo = photos[position]

        holder.binding.apply {

            Glide.with(activity).load(photo.url).into(ivpic)

        }
    }

    override fun getItemCount() = photos.size
}