package com.example.mvisample.network

import com.example.mvisample.MyApp
import com.example.mvisample.utils.toast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 *
 * -----------------------------------------------------------------
 * Copyright (C) 2021, by Sumpay, All rights reserved.
 * -----------------------------------------------------------------
 * desc: NetworkExt
 * Author: wangjp
 * Email: wangjp1@fosun.com
 * Version: Vx.x.x
 * Create: 2022/2/19 5:34 下午
 *
 */
fun <T> Flow<T>.errorCatch(action: suspend FlowCollector<T>.(cause: Throwable) -> Unit): Flow<T> {
    return this.catch {
        if (it is UnknownHostException|| it is SocketTimeoutException){
            MyApp.get().toast("发生网络错误，请稍后重试")
        } else {
            MyApp.get().toast("请求失败，请重试")
        }
        action(it)
    }
}