package com.foodie.melicious.Adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foodie.melicious.Model.CategoryModel
import com.foodie.melicious.databinding.ViewholderListCatBinding

class CategoryListAdapter(
    private val items: MutableList<CategoryModel>,
    private var onItemClick:(Int) -> Unit,

): RecyclerView.Adapter<CategoryListAdapter.ListViewHolder>(){

    inner class ListViewHolder( val binding: ViewholderListCatBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ViewholderListCatBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val item = items[position]
        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)
        holder.binding.titleTxt.text = item.title
        holder.binding.root.setOnClickListener{
            onItemClick(item.id)
        }
    }

    override fun getItemCount() :Int = items.size


}