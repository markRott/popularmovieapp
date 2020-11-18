package com.example.popularmovieapp.ui.adapter.holders

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovieapp.R

class LoadStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val txtErrorMessage = itemView.findViewById<AppCompatTextView>(R.id.tv_error_label)

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            itemView.isVisible = true
            txtErrorMessage.text = loadState.error.localizedMessage
        } else {
            itemView.isVisible = false
        }
    }
}