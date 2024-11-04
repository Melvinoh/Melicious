package com.foodie.melicious.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.foodie.melicious.Helper.FavouriteUtil
import com.foodie.melicious.Helper.ManagmentCart

import com.foodie.melicious.Model.FoodsModel
import com.foodie.melicious.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
   private lateinit var binding: FragmentDetailsBinding
   private lateinit var item : FoodsModel
   private lateinit var managmentCart: ManagmentCart
   private lateinit var FavouriteUtil: FavouriteUtil


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsBinding.inflate(layoutInflater,container,false)

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        managmentCart = ManagmentCart(requireContext())
        FavouriteUtil =FavouriteUtil(requireContext())

        getBundle()
    }
    private fun getBundle(){
        item =  arguments?.getParcelable("item") ?: throw IllegalArgumentException("Item is missing")
        binding.titleTxt2.text = item.title
        binding.descriptionTxt.text = item.description
        binding.priceTxt.text = "kes"+item.price.toString()
        binding.ingridienceTxt.text = item.ingridience.joinToString(" + ")
        Glide.with(requireContext())
            .load(item.picUrl)
            .into(binding.detImage)
        binding.backBtn.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.addToCartBtn.setOnClickListener{
            item.numberInCart = 1
            managmentCart.insertItem(item)
        }
        binding.favBtn.setOnClickListener{
            FavouriteUtil.insertItem(item)
        }
    }


}