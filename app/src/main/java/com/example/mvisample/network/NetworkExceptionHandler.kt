package com.example.mvisample.network

import com.example.mvisample.MyApp
import com.example.mvisample.utils.toast
import kotlinx.coroutines.CoroutineExceptionHandler
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

/**
 *
 * -----------------------------------------------------------------
 * Copyright (C) 2021, by Sumpay, All rights reserved.
 * -----------------------------------------------------------------
 * desc: 通用异常处理,处理一些页面通用的异常逻辑
 * Author: wangjp
 * Email: wangjp1@fosun.com
 * Version: Vx.x.x
 * Create: 2022/2/19 5:40 下午
 *
 */
class NetworkExceptionHandler(private val onException: (e: Throwable) -> Unit = {}) :
    AbstractCoroutineContextElement(
        CoroutineExceptionHandler
    ), CoroutineExceptionHandler {
    override fun handleException(context: CoroutineContext, exception: Throwable) {
        onException.invoke(exception)
        if (exception is UnknownHostException || exception is SocketTimeoutException) {
            MyApp.get().toast("发生网络错误，请稍后重试")
        } else {
            MyApp.get().toast("请求失败，请重试")
        }
    }
}