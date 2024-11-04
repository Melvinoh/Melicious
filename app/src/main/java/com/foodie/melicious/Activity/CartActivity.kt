package com.foodie.melicious.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.foodie.melicious.Adaptor.CartAdapter
import com.foodie.melicious.Helper.ChangeNumberItemsListener
import com.foodie.melicious.Helper.ManagmentCart
import com.foodie.melicious.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {

    private lateinit var binding:ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart

    private var tax:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityCartBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        setVarible()
        caculate()
        initCartList()


    }

    private fun initCartList() {
        with(binding){
            emptyTxt.visibility =
                if(managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView3.visibility =
                if (managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE

        }
        binding.cartList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.cartList.adapter = CartAdapter(managmentCart.getListCart(),this,
            object : ChangeNumberItemsListener {
                override fun onChanged() {
                    caculate()
                }
            }
        )
        binding.checkout.setOnClickListener{
            val intent = Intent(this@CartActivity,CheckoutActivity::class.java)
            startActivity(intent)
        }
    }

    private fun caculate() {

        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round((managmentCart.getTotalFee() * percentTax) *  100)/100.0
        val total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) /100
        val itemTotal = Math.round(managmentCart.getTotalFee()*100)/100

        with(binding){ this
            subTotal.text = "kes $itemTotal"
            taxFee.text = "kes ${tax}"
            deliveryPrice.text = "kes ${delivery}"
            totalPrice.text ="kes $total"
        }

    }

    private fun setVarible() {

    }
}