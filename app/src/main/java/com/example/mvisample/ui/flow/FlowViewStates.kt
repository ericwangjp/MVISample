package com.example.mvisample.ui.flow

/**
 *
 * -----------------------------------------------------------------
 * Copyright (C) 2021, by Sumpay, All rights reserved.
 * -----------------------------------------------------------------
 * desc: FlowViewStates
 * Author: wangjp
 * Email: wangjp1@fosun.com
 * Version: Vx.x.x
 * Create: 2022/2/18 3:06 下午
 *
 */
data class FlowViewState(
    val content: String = "等待网络请求内容",
    val pageStatus: PageStatus = PageStatus.Success
)

sealed class FlowViewEvent {
    data class ShowToast(val message: String) : FlowViewEvent()
    object ShowLoadingDialog : FlowViewEvent()
    object DismissLoadingDialog : FlowViewEvent()
}

sealed class FlowViewAction {
    object PageRequest : FlowViewAction()
    object PartRequest : FlowViewAction()
    object MultiRequest : FlowViewAction()
    object ErrorRequest : FlowViewAction()
}

sealed class PageStatus {
    object Loading : PageStatus()
    object Success : PageStatus()
    data class Error(val throwable: Throwable) : PageStatus()
}