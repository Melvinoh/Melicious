package com.foodie.melicious.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide.init
import com.foodie.melicious.Adaptor.CategoryAdapter
import com.foodie.melicious.Adaptor.CategoryListAdapter
import com.foodie.melicious.Adaptor.PopularAdapter
import com.foodie.melicious.R
import com.foodie.melicious.ViewModel.MainViewModel
import com.foodie.melicious.databinding.FragmentCategoryBinding
import com.foodie.melicious.databinding.FragmentHomeBinding


class CategoryFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCategory()
    }

    private fun initCategory(){
        binding.progressBarListCat.visibility = View.VISIBLE
        viewModel.category.observe(viewLifecycleOwner) {
            binding.categoryList.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,false)
            binding.categoryList.adapter= CategoryListAdapter(it){ id ->

                val fragment = CategoryItemsFragment().apply {
                    arguments = Bundle().apply {
                        putInt("id", id)
                    }
                }
                displayFragment(fragment)

            }
            binding.progressBarListCat.visibility=View.GONE
        }
        viewModel.loadCategory()
    }
    private fun displayFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)//this container is in main activity
            .addToBackStack("null")
            .commit()
    }
}