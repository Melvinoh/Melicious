package com.foodie.melicious.Adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionScene.Transition.TransitionOnClick
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foodie.melicious.Model.CategoryModel
import com.foodie.melicious.databinding.ViewhoderCategoryBinding


class CategoryAdapter(

    private val items: MutableList<CategoryModel>,
    private var onItemClick:(Int) -> Unit,

) : RecyclerView.Adapter<CategoryAdapter.Viewholder>() {

    inner class Viewholder(val binding: ViewhoderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.Viewholder {

        val binding = ViewhoderCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)

    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = items[position]
        holder.binding.titleTxt.text = item.title
        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)
        holder.binding.root.setOnClickListener{
            onItemClick(item.id)
        }
    }

    override fun getItemCount(): Int = items.size
}