package com.amir.calculatorapp.ui.fragments.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amir.calculatorapp.data.History
import com.amir.calculatorapp.databinding.ItemHistoryBinding

class HistoryAdapter(
    private val history: List<History>
) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            ItemHistoryBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun getItemCount() = history.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindPhoto(history[position])
//        holder.itemView.setOnClickListener {
//            listener.onPlaylist(history.items[position])
//        }
    }

    class ViewHolder(private val view: ItemHistoryBinding) : RecyclerView.ViewHolder(view.root) {

        fun bindPhoto(history: History) {
            view.textView.text = history.result
        }
    }
}