package com.foodie.melicious.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.foodie.melicious.R
import com.foodie.melicious.databinding.FragmentShippingBinding


class ShippingFragment : Fragment() {

    private lateinit var binding: FragmentShippingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentShippingBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.update.setOnClickListener{
            displayFragment(PaymentFragment())
        }
        binding.backBtn.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
    private fun displayFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)//this container is in main activity
            .addToBackStack("null")
            .commit()
    }


}