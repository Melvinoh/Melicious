package com.foodie.melicious.Activity

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams

import androidx.fragment.app.Fragment
import com.foodie.melicious.R
import com.foodie.melicious.databinding.ActivityMainBinding
import com.foodie.melicious.fragments.HomeFragment

class MainActivity : BaseActivity() {
    private lateinit var  binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            displayFragment(HomeFragment())
        }
       initBottomMenu()

    }
    override fun onBackPressed() {
        if (binding.bottomNavigation.selectedItemId != R.id.home_btn) {
            binding.bottomNavigation.selectedItemId = R.id.home_btn
        } else {
            super.onBackPressed()
        }
    }

    private fun initBottomMenu() {
        val bottomNav = binding.bottomNavigation
        bottomNav.selectedItemId = R.id.home_btn

        ViewCompat.setOnApplyWindowInsetsListener(bottomNav) { view, insets ->
            val gestureInsets = insets.getInsets(WindowInsetsCompat.Type.systemGestures())
            view.setPadding(0, 0, 0, gestureInsets.bottom)
            insets
        }

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_btn -> {
                    // Navigate to HomeFragment if not already on it
                    if (supportFragmentManager.findFragmentById(binding.fragmentContainer.id) !is HomeFragment) {
                        displayFragment(HomeFragment())
                    }
                    true
                }
                R.id.cart_btn -> {
                    startActivity(Intent(this, CartActivity::class.java))
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    true
                }
                R.id.favourite_btn -> {
                    startActivity(Intent(this, FavouritesActivity::class.java))
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
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

    private fun displayFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()

    }


}