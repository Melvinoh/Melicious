package com.foodie.melicious.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import com.foodie.melicious.R

import com.foodie.melicious.databinding.ActivityMainBinding
import com.foodie.melicious.fragments.AccountDetFragment
import com.foodie.melicious.fragments.HomeFragment

class AccountActivity : BaseActivity() {
    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (savedInstanceState == null) {
            displayFragment(AccountDetFragment())
        }

        initBottomMenu()

    }
    private fun initBottomMenu() {
        val bottomNav = binding.bottomNavigation
        bottomNav.selectedItemId = R.id.account_btn
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
                    startActivity(Intent(this, FavouritesActivity::class.java))
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    true
                }
                R.id.account_btn -> {
                    if (supportFragmentManager.findFragmentById(binding.fragmentContainer.id) !is AccountDetFragment) {
                        displayFragment(AccountDetFragment())
                    }
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