package com.foodie.melicious.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.bumptech.glide.Glide
import com.foodie.melicious.Adaptor.CategoryAdapter
import com.foodie.melicious.Adaptor.PopularAdapter
import com.foodie.melicious.Adaptor.RecomendedAdapter
import com.foodie.melicious.Adaptor.SliderAdapter
import com.foodie.melicious.Model.SliderModel
import com.foodie.melicious.R

import com.foodie.melicious.ViewModel.MainViewModel
import com.foodie.melicious.databinding.FragmentHomeBinding



class HomeFragment : Fragment() {

    private val viewModel:MainViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCategory()
        initPopular()
        initRecommended()
        initBanners()
        initProfile()

        binding.allCat.setOnClickListener{
            displayFragment(CategoryFragment())
        }

    }
    private fun displayFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)//this container is in main activity
            .addToBackStack("null")
            .commit()
    }

    private fun initCategory(){
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(viewLifecycleOwner) {
            binding.viewCategory.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL,false)
            binding.viewCategory.adapter= CategoryAdapter(it){ id ->
                val fragment = CategoryItemsFragment().apply {
                    arguments = Bundle().apply {
                        putInt("id", id)
                    }
                }
                displayFragment(fragment)
            }
            binding.progressBarCategory.visibility=View.GONE
        }
        viewModel.loadCategory()
    }
    private fun initPopular(){
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.popular.observe(viewLifecycleOwner){
            binding.ViewPopular.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
            binding.ViewPopular.adapter =  PopularAdapter(it){ item ->

                val detailsFragment =DetailsFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("item", item)
                    }
                }
                displayFragment(detailsFragment)
            }
            binding.progressBarPopular.visibility = View.GONE
        }
        viewModel.loadPopular()
    }
    private fun initRecommended(){
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.recommended.observe(viewLifecycleOwner){
            binding.ViewRecomended.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
            binding.ViewRecomended.adapter =  RecomendedAdapter(it){ item ->

                val detailsFragment =DetailsFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("item", item)
                    }
                }
                displayFragment(detailsFragment)
            }
            binding.progressBarRec.visibility = View.GONE
        }
        viewModel.loadRecommended()
    }

    private fun initBanners(){
        binding.progressBarBanners.visibility = View.VISIBLE
        viewModel.banner.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                banners(it)
            }
            binding.progressBarBanners.visibility = View.GONE
        }
        viewModel.loadBanners()

    }
    private fun initProfile() {
        viewModel.userProfile.observe(viewLifecycleOwner) { userList ->
            userList?.let {
                if (it.isNotEmpty()) {
                    val user = it[0]

                    if (!user?.profileImgUri.isNullOrEmpty()) {
                        Glide.with(this)
                            .load(Uri.parse(user?.profileImgUri)) // Replace with Glide for better handling
                            .placeholder(R.drawable.default_profile_image) // optional placeholder
                            .error(R.drawable.default_profile_image) // optional error image
                            .into(binding.profilePic)
                    } else {
                        binding.profilePic.setImageResource(R.drawable.default_profile_image)
                    }
                }
            }
        }
    }


    private fun banners(it:List<SliderModel>) {
        binding.ViewPager2.adapter = SliderAdapter(it, binding.ViewPager2)
        binding.ViewPager2.clipToPadding=false
        binding.ViewPager2.clipChildren = false
        binding.ViewPager2.offscreenPageLimit = 2
        binding.ViewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(20))
        }
        binding.ViewPager2.setPageTransformer(compositePageTransformer)
        if (it.size > 1){
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.ViewPager2)
        }

    }


}