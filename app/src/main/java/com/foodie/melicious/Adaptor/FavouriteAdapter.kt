package com.foodie.melicious.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.foodie.melicious.Helper.FavouriteUtil

import com.foodie.melicious.Model.FoodsModel
import com.foodie.melicious.databinding.ViewholderFavouritesBinding

class FavouriteAdapter(
    private val item :ArrayList<FoodsModel>,
    context: Context,
): RecyclerView.Adapter<FavouriteAdapter.Viewholder>() {
    inner class Viewholder(val binding: ViewholderFavouritesBinding ):RecyclerView.ViewHolder(binding.root)

    private val FavouriteUtil = FavouriteUtil(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = ViewholderFavouritesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteAdapter.Viewholder, position: Int) {
        holder.binding.titleTxt2.text = item[position].title
        holder.binding.priceTxt.text = item[position].price.toString()

        Glide.with(holder.itemView.context)
            .load(item[position].picUrl)
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.detImage)
    }

    override fun getItemCount(): Int = item.size




}