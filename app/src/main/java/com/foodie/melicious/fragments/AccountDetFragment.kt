package com.foodie.melicious.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.foodie.melicious.R
import com.foodie.melicious.ViewModel.MainViewModel
import com.foodie.melicious.databinding.FragmentAccountDetBinding
import com.foodie.melicious.databinding.FragmentHomeBinding

class AccountDetFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentAccountDetBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountDetBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.account.setOnClickListener{
            displayFragment(EditProfileFragment())
        }

        initProfile()

    }

    private fun initProfile() {
        viewModel.userProfile.observe(viewLifecycleOwner){
             userList -> userList?.let{
                 if (it.isNotEmpty()){
                     val user = it[0]
                     binding.fullname1.text = user?.fullName
                     binding.email.text = user?.emailAddress.toString()

                     if (!user?.profileImgUri.isNullOrEmpty()) {
                         Glide.with(this)
                             .load(Uri.parse(user?.profileImgUri)) // Replace with Glide for better handling
                             .placeholder(R.drawable.default_profile_image) // optional placeholder
                             .error(R.drawable.default_profile_image) // optional error image
                             .into(binding.profilePic1)
                     } else {

                         binding.profilePic1.setImageResource(R.drawable.default_profile_image)
                     }
                 }
             }
        }
        val userId = 1
        viewModel.loadUserProfile(userId)
    }

    private fun displayFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)//this container is in main activity
            .addToBackStack("null")
            .commit()
    }


}