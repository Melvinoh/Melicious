package com.foodie.melicious.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment

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
        binding.cartBtn.setOnClickListener{
            startActivity(Intent(this@AccountActivity, MainActivity ::class.java))
        }
        binding.homeBtn.setOnClickListener{
            startActivity(Intent(this@AccountActivity, MainActivity ::class.java ))
        }
    }
    private fun displayFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()

    }
}