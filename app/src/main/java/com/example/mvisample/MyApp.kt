package com.example.mvisample

import android.app.Application
import com.drake.statelayout.StateConfig

/**
 *
 * -----------------------------------------------------------------
 * Copyright (C) 2021, by Sumpay, All rights reserved.
 * -----------------------------------------------------------------
 * desc: MyApp
 * Author: wangjp
 * Email: wangjp1@fosun.com
 * Version: Vx.x.x
 * Create: 2022/2/14 3:57 下午
 *
 */
class MyApp : Application() {
    companion object {
        lateinit var instance: MyApp
        fun get(): MyApp {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        StateConfig.apply {
            loadingLayout = R.layout.layout_loading
            errorLayout = R.layout.layout_error
        }
    }
}