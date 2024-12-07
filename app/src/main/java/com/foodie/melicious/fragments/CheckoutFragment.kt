package com.foodie.melicious.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.foodie.melicious.Activity.CartActivity
import com.foodie.melicious.Adaptor.TabsAdapter
import com.foodie.melicious.R
import com.foodie.melicious.databinding.FragmentCheckoutBinding
import com.google.android.material.tabs.TabLayoutMediator


class CheckoutFragment : Fragment() {

   private lateinit var  binding : FragmentCheckoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckoutBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTabs()

        binding.backBtn.setOnClickListener {
            val intent = Intent(requireContext(), CartActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            startActivity(intent)
        }
    }

    private fun initTabs() {
        val adapter = TabsAdapter(this)
        binding.ViewPager2.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.ViewPager2) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.mastercard)
                1 -> tab.setIcon(R.drawable.mpesa2)
                2 -> tab.setIcon(R.drawable.visacard)
            }
        }.attach()
    }

}