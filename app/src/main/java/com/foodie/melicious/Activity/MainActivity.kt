package com.foodie.melicious.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.foodie.melicious.ViewModel.MainViewModel
import com.foodie.melicious.databinding.ActivityMainBinding
import com.foodie.melicious.fragments.HomeFragment

class MainActivity : BaseActivity() {
    private lateinit var  binding: ActivityMainBinding

    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            displayFragment(HomeFragment())
        }
        initBottomMenu()

    }

    private fun initBottomMenu() {
        binding.cartBtn.setOnClickListener{
              startActivity(Intent(this@MainActivity, CartActivity::class.java))
        }
        binding.accountBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity,AccountActivity::class.java ))
        }
        binding.favBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity,FavouritesActivity::class.java ))
        }
    }

    private fun displayFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)

            .commit()

    }


}