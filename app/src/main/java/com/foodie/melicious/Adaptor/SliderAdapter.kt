package com.foodie.melicious.Adaptor


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.foodie.melicious.Model.SliderModel
import com.foodie.melicious.R

class SliderAdapter(
    private var sliderItems: List<SliderModel>,
    private val viewPager2: ViewPager2

) : RecyclerView.Adapter<SliderAdapter.SliderViewholder>() {

    class SliderViewholder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private val imageView :ImageView = itemView.findViewById(R.id.imageSlider)

        fun setImage(sliderModel: SliderModel) {
            Glide.with(itemView.context)
                .load(sliderModel.picUrl)
                .into(imageView)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewholder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.slider_item_container,parent,false)
        return SliderViewholder(view)

    }

    override fun onBindViewHolder(holder: SliderViewholder, position: Int) {
       holder.setImage(sliderItems[position])
        if (position == sliderItems.size - 1) {
            viewPager2.post {
                viewPager2.setCurrentItem(0, true)
            }
        }
    }

    override fun getItemCount(): Int = sliderItems.size

}