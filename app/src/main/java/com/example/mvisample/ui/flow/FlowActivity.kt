package com.example.mvisample.ui.flow

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mvisample.R
import com.example.mvisample.observeEvent
import com.example.mvisample.observeState
import com.example.mvisample.utils.toast
import kotlinx.android.synthetic.main.activity_flow.*

class FlowActivity : AppCompatActivity() {
    private val viewModel by viewModels<FlowViewModel>()
    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.viewStates.let { state ->
            state.observeState(this, FlowViewState::pageStatus) {
                when (it) {
                    is PageStatus.Success -> state_layout.showContent()
                    is PageStatus.Loading -> state_layout.showLoading()
                    is PageStatus.Error -> state_layout.showError()
                }
            }
            state.observeState(this, FlowViewState::content) {
                tv_content.text = it
            }
        }

        viewModel.viewEvents.observeEvent(this) {
            when (it) {
                is FlowViewEvent.ShowToast -> toast(it.message)
                is FlowViewEvent.ShowLoadingDialog -> showLoadingDialog()
                is FlowViewEvent.DismissLoadingDialog -> dismissLoadingDialog()
            }
        }
    }

    private fun showLoadingDialog() {
        progressDialog.show()
    }

    private fun dismissLoadingDialog() {
        progressDialog.takeIf { it.isShowing }?.dismiss()
    }

    fun simpleRequest(view: View) {
        viewModel.dispatch(FlowViewAction.PageRequest)
    }

    fun partRequest(view: View) {
        viewModel.dispatch(FlowViewAction.PartRequest)
    }

    fun multiSource(view: View) {
        viewModel.dispatch(FlowViewAction.MultiRequest)
    }

    fun errorRequest(view: View) {
        viewModel.dispatch(FlowViewAction.ErrorRequest)
    }
}