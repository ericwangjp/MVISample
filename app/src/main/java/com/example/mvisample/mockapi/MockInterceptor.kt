package com.example.mvisample.mockapi

import com.example.mvisample.BuildConfig
import okhttp3.*

/**
 *
 * -----------------------------------------------------------------
 * Copyright (C) 2021, by Sumpay, All rights reserved.
 * -----------------------------------------------------------------
 * desc: MockInterceptor
 * Author: wangjp
 * Email: wangjp1@fosun.com
 * Version: Vx.x.x
 * Create: 2022/2/16 5:36 下午
 *
 */
class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url().uri().toString()
            val responseString = when {
                uri.endsWith("mock") -> getMockApiResponse
                else -> ""
            }
            return Response.Builder()
                .request(chain.request())
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    ResponseBody.create(
                        MediaType.parse("application/json"),
                        responseString.toByteArray()
                    )
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            throw IllegalAccessError("MockInterceptor is only meant for Testing Purposes and bound to be used only with DEBUG mode")
        }
    }
}

const val getMockApiResponse = """
{
  "articles": [
    {
      "title": "大哥哥",
      "description": "Description1",
      "imageUrl": "imageUrl"
    },
    {
      "title": "小姐姐",
      "description": "Description2",
      "imageUrl": "imageUrl"
    },
    {
      "title": "臭弟弟",
      "description": "Description3",
      "imageUrl": "imageUrl"
    }
  ]
}"""