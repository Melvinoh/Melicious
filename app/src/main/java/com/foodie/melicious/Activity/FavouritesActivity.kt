package com.foodie.melicious.Activity


import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.foodie.melicious.Adaptor.FavouriteAdapter
import com.foodie.melicious.Helper.FavouriteUtil
import com.foodie.melicious.databinding.ActivityFavouritesBinding

class FavouritesActivity :  BaseActivity() {

    private lateinit var binding: ActivityFavouritesBinding
    private lateinit var FavouriteUtil: FavouriteUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        FavouriteUtil = FavouriteUtil(this)

        setContentView(binding.root)

        initFavouriteList()

    }
    private fun initFavouriteList() {
        with(binding){
            emptyTxt.visibility =
                if(FavouriteUtil.getListFavourite().isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
            scrollView3.visibility =
                if (FavouriteUtil.getListFavourite().isEmpty()) android.view.View.GONE else android.view.View.VISIBLE

        }
        binding.favouriteList.layoutManager = GridLayoutManager(this,2)
        binding.favouriteList.adapter = FavouriteAdapter(FavouriteUtil.getListFavourite(),this)

    }

}