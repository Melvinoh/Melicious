package com.foodie.melicious.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.foodie.melicious.Helper.ChangeNumberItemsListener
import com.foodie.melicious.Helper.ManagmentCart


import com.foodie.melicious.Model.FoodsModel
import com.foodie.melicious.databinding.CartItemBinding


class CartAdapter(
    private val item :ArrayList<FoodsModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener? = null
) : RecyclerView.Adapter<CartAdapter.Viewholder>() {

   inner class Viewholder(val binding: CartItemBinding):RecyclerView.ViewHolder(binding.root)
    private val managmentCart = ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
       val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Viewholder(binding)
    }

    override fun getItemCount(): Int = item.size


    override fun onBindViewHolder(holder: Viewholder, position: Int) {
       holder.binding.titleTxt.text= item[position].title
        holder.binding.priceTxt.text= "kes ${Math.round(item[position].numberInCart * item[position].price)}"
        holder.binding.cartNumberTxt.text =item[position].numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(item[position].picUrl)
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.pic)

        holder.binding.plus.setOnClickListener{
            managmentCart.plusItem(item,position,object : ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }
        holder.binding.minus.setOnClickListener{
            managmentCart.minusItem(item,position,object : ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }
    }


}