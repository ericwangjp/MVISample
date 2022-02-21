package com.example.mvisample.ui.function

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mvisample.R
import com.example.mvisample.ui.flow.FlowActivity
import com.example.mvisample.ui.main.MainActivity

class FunctionListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function_list)
    }

    fun mviSample(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun flowSample(view: View) {
        startActivity(Intent(this, FlowActivity::class.java))
    }
}