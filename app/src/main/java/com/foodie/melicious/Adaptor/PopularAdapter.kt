package com.foodie.melicious.Adaptor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foodie.melicious.Model.FoodsModel
import com.foodie.melicious.databinding.ViewholderPopularBinding

class PopularAdapter (
    private val items : MutableList<FoodsModel>,
    private var onItemClick : (FoodsModel) -> Unit,
):RecyclerView.Adapter<PopularAdapter.ViewHolder>(){

        inner class ViewHolder( val binding: ViewholderPopularBinding) :
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderPopularBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.titleTxt.text = items[position].title
        val ingredientsText = items[position].ingridience.joinToString(" + ")
        holder.binding.descriptionTxt.text = ingredientsText
        holder.binding.priceTxt.text = "kes"+items[position].price.toString()

        Glide.with(holder.itemView.context)
            .load(items[position].picUrl)
            .into(holder.binding.pic)
        holder.binding.root.setOnClickListener{
            onItemClick(items[position])
        }
    }


}