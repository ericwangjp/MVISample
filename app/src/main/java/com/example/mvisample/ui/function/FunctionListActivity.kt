package com.example.mvisample.ui.function

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import com.example.mvisample.R
import com.example.mvisample.ui.flow.FlowActivity
import com.example.mvisample.ui.main.MainActivity

class FunctionListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function_list)
//        配合 android:fitsSystemWindows="true" 实现沉浸式状态栏
//        window.statusBarColor = Color.TRANSPARENT
//        val clayout = findViewById<ConstraintLayout>(R.id.layout_root)
////        WindowInsetsController WindowInsets
//        clayout.systemUiVisibility =
//            (SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btn_mvi)) { view, insets ->
//            val params = view.layoutParams as FrameLayout.LayoutParams
//            params.topMargin = insets.systemWindowInsetTop
//            insets
//        }
    }

    fun mviSample(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun flowSample(view: View) {
        startActivity(Intent(this, FlowActivity::class.java))
    }
}