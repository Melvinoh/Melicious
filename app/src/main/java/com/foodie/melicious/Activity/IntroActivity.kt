package com.foodie.melicious.Activity

import android.content.Intent
import android.os.Bundle
import com.foodie.melicious.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.login.setOnClickListener {
            val intent  = Intent(this@IntroActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.signup.setOnClickListener {
            val intent  = Intent(this@IntroActivity, SignupActivity::class.java)
            startActivity(intent)
        }

    }
}