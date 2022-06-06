package com.otus.vmikhaylov

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "FilmsAdapter"

class FilmsAdapter(
    private val items: List<Film>,
    private val listener: FilmClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d(TAG, "onCreateViewHolder: $viewType")

        val inflater = LayoutInflater.from(parent.context)
        return FilmViewHolder(inflater.inflate(R.layout.item_film, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: $position")

        when (holder) {
            is FilmViewHolder -> {
                holder.bind(items[position], listener)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    interface FilmClickListener {
        fun onNewsClick(film: Film, position: Int)
        fun onFavoriteClick(film: Film, position: Int)
    }
}