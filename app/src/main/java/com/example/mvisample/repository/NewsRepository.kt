package com.example.mvisample.repository

import com.example.mvisample.mockapi.MockApi
import com.example.mvisample.utils.PageState
import kotlinx.coroutines.delay
import java.lang.Exception

/**
 *
 * -----------------------------------------------------------------
 * Copyright (C) 2021, by Sumpay, All rights reserved.
 * -----------------------------------------------------------------
 * desc: NewsRepository
 * Author: wangjp
 * Email: wangjp1@fosun.com
 * Version: Vx.x.x
 * Create: 2022/2/16 4:52 下午
 *
 */
class NewsRepository {
    companion object {
        @Volatile
        private var instance: NewsRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: NewsRepository().also {
                instance = it
            }
        }
    }

    suspend fun getMockApiResponse(): PageState<List<NewsItem>> {
        val articlesApiResult = try {
            delay(2000)
            MockApi.create().getLatestNews()
        } catch (e: Exception) {
            return PageState.Error(e)
        }

        articlesApiResult.articles?.let {
            return PageState.Success(data = it)
        } ?: run {
            return PageState.Error("Failed to get News")
        }
    }
}