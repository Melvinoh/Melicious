package com.foodie.melicious.Activity

import android.os.Bundle
import android.view.WindowManager.LayoutParams.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            FLAG_LAYOUT_NO_LIMITS,
            FLAG_LAYOUT_NO_LIMITS
        )
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
    }
}


