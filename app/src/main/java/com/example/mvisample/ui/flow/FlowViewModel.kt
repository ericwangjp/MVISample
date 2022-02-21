package com.example.mvisample.ui.flow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvisample.asLiveData
import com.example.mvisample.network.errorCatch
import com.example.mvisample.setEvent
import com.example.mvisample.setState
import com.zj.architecture.utils.SingleLiveEvents
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.RuntimeException

/**
 *
 * -----------------------------------------------------------------
 * Copyright (C) 2021, by Sumpay, All rights reserved.
 * -----------------------------------------------------------------
 * desc: FlowViewModel
 * Author: wangjp
 * Email: wangjp1@fosun.com
 * Version: Vx.x.x
 * Create: 2022/2/18 3:04 下午
 *
 */
class FlowViewModel : ViewModel() {
    private val _viewStates = MutableLiveData(FlowViewState())
    val viewStates = _viewStates.asLiveData()
    private val _viewEvents: SingleLiveEvents<FlowViewEvent> = SingleLiveEvents()
    val viewEvents = _viewEvents.asLiveData()

    fun dispatch(viewAction: FlowViewAction) {
        when (viewAction) {
            is FlowViewAction.PageRequest -> pageRequest()
            is FlowViewAction.PartRequest -> partRequest()
            is FlowViewAction.MultiRequest -> multiRequest()
            is FlowViewAction.ErrorRequest -> errorRequest()
        }
    }

    /**
     * 页面请求，通常包括刷新页面loading状态等
     */
    private fun pageRequest() {
        viewModelScope.launch {
            flow {
                delay(2000)
                emit("页面请求成功")
            }.onStart {
                _viewStates.setState {
                    copy(pageStatus = PageStatus.Loading)
                }
            }.onEach {
                _viewStates.setState {
                    copy(content = it, pageStatus = PageStatus.Success)
                }
            }.errorCatch {
                _viewStates.setState {
                    copy(pageStatus = PageStatus.Error(it))
                }
            }.collect()
        }
    }

    /**
     * 页面局部请求，例如点赞收藏等，通常需要弹dialog或toast
     */
    private fun partRequest() {
        viewModelScope.launch {
            flow {
                delay(2000)
                emit("收藏成功")
            }.onStart {
                _viewEvents.setEvent(FlowViewEvent.ShowLoadingDialog)
            }.onEach {
                _viewEvents.setEvent(
                    FlowViewEvent.DismissLoadingDialog,
                    FlowViewEvent.ShowToast(it)
                )
                _viewStates.setState {
                    copy(content = it)
                }
            }.errorCatch {
                _viewEvents.setEvent(FlowViewEvent.DismissLoadingDialog)
            }.collect()
        }
    }

    /**
     * 多数据源请求
     */
    private fun multiRequest() {
        viewModelScope.launch {
            var flow1 = flow {
                delay(1000)
                emit("数据源1")
            }
            var flow2 = flow {
                delay(2000)
                emit("数据源2")
            }

            flow1.zip(flow2) { a, b ->
                "$a,$b"
            }.onStart {
                _viewEvents.setEvent(FlowViewEvent.ShowLoadingDialog)
            }.onEach {
                _viewEvents.setEvent(
                    FlowViewEvent.DismissLoadingDialog,
                    FlowViewEvent.ShowToast(it)
                )
                _viewStates.setState {
                    copy(content = it)
                }
            }.errorCatch {
                _viewEvents.setEvent(FlowViewEvent.DismissLoadingDialog)
            }.collect()
        }
    }

    /**
     * 请求错误示例
     */
    private fun errorRequest() {
        viewModelScope.launch {
            flow {
                delay(2000)
                emit("请求失败")
                throw RuntimeException("error is occured")
            }.onStart {
                _viewEvents.setEvent(FlowViewEvent.ShowLoadingDialog)
            }.onEach {
                _viewEvents.setEvent(
                    FlowViewEvent.DismissLoadingDialog,
                    FlowViewEvent.ShowToast(it)
                )
                _viewStates.setState {
                    copy(content = it)
                }
            }.errorCatch {
                _viewEvents.setEvent(FlowViewEvent.DismissLoadingDialog)
            }.collect()
        }
    }
}