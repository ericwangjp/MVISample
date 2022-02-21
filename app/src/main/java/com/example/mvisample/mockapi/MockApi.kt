package com.example.mvisample.mockapi

import com.example.mvisample.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 *
 * -----------------------------------------------------------------
 * Copyright (C) 2021, by Sumpay, All rights reserved.
 * -----------------------------------------------------------------
 * desc: MockApi
 * Author: wangjp
 * Email: wangjp1@fosun.com
 * Version: Vx.x.x
 * Create: 2022/2/16 5:32 下午
 *
 */
interface MockApi {
    @GET("mock")
    suspend fun getLatestNews(): MockApiResponse

    companion object {
        fun create(): MockApi {
            val okHttpClient = OkHttpClient().newBuilder().addInterceptor(MockInterceptor()).build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MockApi::class.java)
        }
    }
}