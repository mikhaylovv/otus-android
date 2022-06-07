package com.otus.vmikhaylov.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otus.vmikhaylov.Film
import com.otus.vmikhaylov.FilmViewHolder
import com.otus.vmikhaylov.R

private const val TAG = "FavoritesFilmsAdapter"

class FavoritesFilmsAdapter(
    private val items: List<Film>,
    private val listener: FavoriteFilmClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavoriteFilmViewHolder(inflater.inflate(R.layout.item_film, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FavoriteFilmViewHolder -> {
                holder.bind(items[position], listener)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    interface FavoriteFilmClickListener {
        fun onDeleteFavoriteClick(film: Film, position: Int)
        fun onDetailsClick(film: Film, position: Int)
    }
}