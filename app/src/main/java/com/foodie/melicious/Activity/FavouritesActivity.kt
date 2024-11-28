package com.foodie.melicious.Activity


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.foodie.melicious.Adaptor.FavouriteAdapter
import com.foodie.melicious.Helper.FavouriteUtil
import com.foodie.melicious.R
import com.foodie.melicious.databinding.ActivityFavouritesBinding
import com.foodie.melicious.fragments.HomeFragment

class FavouritesActivity :  BaseActivity() {

    private lateinit var binding: ActivityFavouritesBinding
    private lateinit var FavouriteUtil: FavouriteUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        FavouriteUtil = FavouriteUtil(this)

        setContentView(binding.root)

        initFavouriteList()
        initBottomMenu()

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
    private fun initBottomMenu() {
        val bottomNav = binding.bottomNavigation
        bottomNav.selectedItemId = R.id.favourite_btn
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_btn -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    true
                }
                R.id.cart_btn -> {
                    startActivity(Intent(this, CartActivity::class.java))
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    true
                }
                R.id.favourite_btn -> {
                    true
                }
                R.id.account_btn -> {
                    startActivity(Intent(this, AccountActivity::class.java))
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    true
                }
                else -> false
            }
        }
    }
}