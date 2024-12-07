package com.foodie.melicious.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.foodie.melicious.databinding.ActivityCheckoutBinding
import com.foodie.melicious.fragments.CheckoutFragment


class CheckoutActivity :  BaseActivity() {

    private lateinit var binding : ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCheckoutBinding.inflate(layoutInflater)

        setContentView(binding.root)
        if (savedInstanceState == null) {
            displayFragment(CheckoutFragment())
        }
    }
    private fun displayFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer1.id, fragment)
            .addToBackStack(null)
            .commit()
    }

}