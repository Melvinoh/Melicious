package com.foodie.melicious.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.foodie.melicious.Activity.LoginActivity
import com.foodie.melicious.R
import com.foodie.melicious.ViewModel.MainViewModel
import com.foodie.melicious.databinding.FragmentAccountDetBinding
import com.foodie.melicious.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth

class AccountDetFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentAccountDetBinding
    private lateinit var firebaseAuth: FirebaseAuth



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
        binding.logout.setOnClickListener{
            logoutUser()
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
                             .load(Uri.parse(user?.profileImgUri))
                             .placeholder(R.drawable.default_profile_image)
                             .error(R.drawable.default_profile_image)
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
    private fun logoutUser() {

        FirebaseAuth.getInstance().signOut()

        val sharedPreferences = requireContext().getSharedPreferences("userDb", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)

        requireActivity().finish()
    }


}