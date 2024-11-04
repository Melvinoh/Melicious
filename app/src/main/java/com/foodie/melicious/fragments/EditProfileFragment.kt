package com.foodie.melicious.fragments


import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.foodie.melicious.Helper.UserDb
import com.foodie.melicious.Model.UserModel
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.foodie.melicious.R
import com.foodie.melicious.ViewModel.MainViewModel
import kotlinx.coroutines.launch
import com.foodie.melicious.databinding.FragmentEditProfileBinding
import com.google.firebase.auth.FirebaseAuth



class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private lateinit var imageLauncher : ActivityResultLauncher<String>
    private val viewModel: MainViewModel by activityViewModels()

    private var profilePic: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditProfileBinding.inflate(layoutInflater,container,false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){

            Uri : Uri? -> Uri?.let{
                requireContext().contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                try {
                    val inputStream = requireContext().contentResolver.openInputStream(it)
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    binding.profilePic2.setImageBitmap(bitmap)
                    profilePic = it.toString()
                } catch (e: Exception) {
                    e.printStackTrace() // Handle any exceptions during image loading
                }
            }
        }
        savedInstanceState?.let {
            profilePic = it.getString("profilePicUri")
            profilePic?.let { uri ->
                binding.profilePic2.setImageURI(Uri.parse(uri))
            }
        }
        initProfile()

        binding.backBtn.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.cameraEt.setOnClickListener{
            imageLauncher.launch("image/*")

        }

        binding.update.setOnClickListener{
            formSubmit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("profilePicUri", profilePic)
    }



    private fun formSubmit() {

        val userDB: UserDb = UserDb.getDatabase(requireContext())

        val fname = binding.fName.text.toString().trim()
        val email = binding.emailEt.text.toString().trim()
        val mobile = binding.mobileEt.text.toString().trim()
        val country = binding.countryEt.toString().trim()
        val dob = binding.dobEt.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailEt.error ="invalid email format"
            return
        }else if(TextUtils.isEmpty(email)){
            binding.fName.error = "please enter email"
            return
        }else if(TextUtils.isEmpty(fname)){
            binding.fName.error = "please enter name"
            return
        }
        val userProfile = UserModel(1,profilePic,fname,email,mobile,country,dob)

        lifecycleScope.launch {
            userDB.UserDao().updateUser(userProfile)
        }
    }
    private fun initProfile() {
        viewModel.userProfile.observe(viewLifecycleOwner) { userList ->
            userList?.let {
                if (it.isNotEmpty()) {
                    val user = it[0]
                    binding.fullname.text = user?.fullName
                    binding.fName.setText( user?.fullName)
                    binding.emailEt.setText(user?.emailAddress)
                    binding.mobileEt.setText(user?.mobile)
                    binding.countryEt.setText(user?.country)
                    binding.dobEt.setText(user?.country)

                    if (!user?.profileImgUri.isNullOrEmpty()) {
                        Glide.with(this)
                            .load(Uri.parse(user?.profileImgUri)) // Replace with Glide for better handling
                            .placeholder(R.drawable.default_profile_image) // optional placeholder
                            .error(R.drawable.default_profile_image) // optional error image
                            .into(binding.profilePic2)
                    } else {

                        binding.profilePic2.setImageResource(R.drawable.default_profile_image)
                    }
                }
            }
        }
    }

}