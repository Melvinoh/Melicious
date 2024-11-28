package com.foodie.melicious.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.foodie.melicious.databinding.ActivityCheckoutBinding
import com.foodie.melicious.fragments.CardFragment


class CheckoutActivity :  BaseActivity() {

    private lateinit var binding : ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCheckoutBinding.inflate(layoutInflater)

        setContentView(binding.root)
        if (savedInstanceState == null) {
            displayFragment(CardFragment())

        }

    }

    private fun displayFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }

}