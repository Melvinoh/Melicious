package com.foodie.melicious.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.foodie.melicious.Adaptor.PopularAdapter
import com.foodie.melicious.R
import com.foodie.melicious.ViewModel.MainViewModel
import com.foodie.melicious.databinding.FragmentCategoryItemsBinding



class CategoryItemsFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels()
    private lateinit var binding: FragmentCategoryItemsBinding
    private var id :Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryItemsBinding.inflate(layoutInflater,container,false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFoodItems()
        binding.backBtn.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun initFoodItems() {
        binding.progressBarItems.visibility =View.VISIBLE
        viewModel.categoryItems.observe(viewLifecycleOwner){
            binding.viewCategoryItems.layoutManager = GridLayoutManager(context,2)
            binding.viewCategoryItems.adapter = PopularAdapter(it){ item ->
                val detailsFragment =DetailsFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("item", item)
                    }
                }
                displayFragment(detailsFragment)
            }
            binding.progressBarItems.visibility = View.GONE
        }
        id = arguments?.getInt("id") ?: 0

        viewModel.loadCategoryItems(id)

    }
    private fun displayFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)//this container is in main activity
            .addToBackStack("null")
            .commit()
    }


}