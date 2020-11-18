package com.example.popularmovieapp.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovieapp.R
import com.example.popularmovieapp.inflate

class ErrorStateAdapter : LoadStateAdapter<ErrorStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder =
            LoadStateViewHolder(parent.inflate(R.layout.item_error_state))

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class LoadStateViewHolder(itemView: View, ) : RecyclerView.ViewHolder(itemView) {

        private val txtErrorMessage = itemView.findViewById<AppCompatTextView>(R.id.tv_error_label)

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                txtErrorMessage.text = loadState.error.localizedMessage
            }
        }
    }
}