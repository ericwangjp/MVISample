package com.example.mvisample.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 *
 * -----------------------------------------------------------------
 * Copyright (C) 2021, by Sumpay, All rights reserved.
 * -----------------------------------------------------------------
 * desc: CoroutineScopeHelper
 * Author: wangjp
 * Email: wangjp1@fosun.com
 * Version: Vx.x.x
 * Create: 2022/2/19 5:46 下午
 *
 */
class CoroutineScopeHelper<T>(private val coroutineScope: CoroutineScope) {
    fun execute(init: LaunchBuilder<T>.() -> Unit): Job {
        val result = LaunchBuilder<T>().apply(init)
        val handler = NetworkExceptionHandler {
            result.onError?.invoke(it)
        }
        return coroutineScope.launch(handler) {
            val res: T = result.onRequest()
            result.onSuccess?.invoke(res)
        }
    }
}

class LaunchBuilder<T> {
    lateinit var onRequest: (suspend () -> T)
    var onSuccess: ((data: T) -> Unit)? = null
    var onError: ((Throwable) -> Unit)? = null
}

fun <T> CoroutineScope.netLaunch(init: LaunchBuilder<T>.() -> Unit) {
    val scopeHelper = CoroutineScopeHelper<T>(this)
    scopeHelper.execute(init)
}