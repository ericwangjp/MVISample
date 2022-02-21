package com.example.mvisample.mockapi

import com.example.mvisample.repository.NewsItem

/**
 *
 * -----------------------------------------------------------------
 * Copyright (C) 2021, by Sumpay, All rights reserved.
 * -----------------------------------------------------------------
 * desc: MockApiResponse
 * Author: wangjp
 * Email: wangjp1@fosun.com
 * Version: Vx.x.x
 * Create: 2022/2/16 5:30 下午
 *
 */
data class MockApiResponse(val articles: List<NewsItem>? = null)
