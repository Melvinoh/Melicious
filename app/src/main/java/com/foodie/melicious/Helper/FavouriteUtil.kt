package com.foodie.melicious.Helper

import android.content.Context
import android.widget.Toast
import com.foodie.melicious.Model.FoodsModel

class FavouriteUtil(val context: Context) {
    private val tinyDB = TinyDB(context)

    fun insertItem(item: FoodsModel) {
        var listItem = getListFavourite()
        val existAlready = listItem.any { it.title == item.title}
        val index = listItem.indexOfFirst { it.title == item.title }

        if (existAlready) {
            listItem[index].numberInCart = item.numberInCart
        } else {
            listItem.add(item)
        }
        tinyDB.putListObject("FavouriteList", listItem)
        Toast.makeText(context, "Added to favourites", Toast.LENGTH_SHORT).show()
    }

    fun getListFavourite(): java.util.ArrayList<FoodsModel> {
        return tinyDB.getListObject("FavouriteList") ?: arrayListOf()
    }
    fun minusItem(listItem: ArrayList<FoodsModel>, position: Int, listener: ChangeNumberItemsListener) {
        if (listItem[position].numberInCart == 1) {
            listItem.removeAt(position)
        } else {
            listItem[position].numberInCart--
        }
        tinyDB.putListObject("FavouriteList", listItem)
        listener.onChanged()
    }














}