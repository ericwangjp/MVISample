package com.example.mvisample.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mvisample.R
import com.example.mvisample.observeState
import com.example.mvisample.repository.NewsItem
import com.example.mvisample.utils.FetchStatus
import com.example.mvisample.utils.toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val newsRvAdapter by lazy {
        NewsRvAdapter {
            viewModel.dispatch(MainViewAction.NewsItemClicked(it.tag as NewsItem))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initViewModel()
    }

    private fun initView() {
        rv_home.adapter = newsRvAdapter

        layout_swipe_refresh.setOnRefreshListener {
            viewModel.dispatch(MainViewAction.OnSwipeRefresh)
        }

        fab_star.setOnClickListener {
            viewModel.dispatch(MainViewAction.FabClicked)
        }
    }

    private fun initViewModel() {
        viewModel.viewStates.run {
            observeState(this@MainActivity, MainViewState::newsList) {
                newsRvAdapter.submitList(it)
            }

            observeState(this@MainActivity, MainViewState::fetchStatus) {
                when (it) {
                    is FetchStatus.Fetched -> {
                        layout_swipe_refresh.isRefreshing = false
                    }
                    is FetchStatus.NotFetched -> {
                        viewModel.dispatch(MainViewAction.FetchNews)
                        layout_swipe_refresh.isRefreshing = false
                    }
                    is FetchStatus.Fetching -> {
                        layout_swipe_refresh.isRefreshing = true
                    }
                }
            }
        }

        viewModel.viewEvents.observe(this) {
            renderViewEvent(it)
        }
    }

    private fun renderViewEvent(event: MainViewEvent) {
        when (event) {
            is MainViewEvent.ShowSnackBar -> {
                Snackbar.make(layout_coordinator_root, event.message, Snackbar.LENGTH_SHORT).show()
            }
            is MainViewEvent.ShowToast -> {
                toast(event.message)
            }
        }
    }
}