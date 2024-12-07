package com.foodie.melicious.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.foodie.melicious.R
import com.foodie.melicious.databinding.FragmentMpesaBinding


class MpesaFragment : Fragment() {

    private lateinit var  binding : FragmentMpesaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMpesaBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.next.setOnClickListener{
            displayFragment(ShippingFragment())
        }
    }
    private fun displayFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container1, fragment)
            .addToBackStack(null)
            .commit()
    }

}
