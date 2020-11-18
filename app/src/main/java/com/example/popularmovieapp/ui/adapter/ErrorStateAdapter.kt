package com.example.popularmovieapp.ui.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.popularmovieapp.R
import com.example.popularmovieapp.ui.adapter.holders.LoadStateViewHolder
import com.example.popularmovieapp.utils.inflate

class ErrorStateAdapter : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder =
            LoadStateViewHolder(parent.inflate(R.layout.item_error_state))

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}