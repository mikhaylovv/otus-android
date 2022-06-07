package com.otus.vmikhaylov.favorites

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.otus.vmikhaylov.Film
import com.otus.vmikhaylov.R

class FavoriteFilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.title)
    private val image: ImageView = itemView.findViewById(R.id.image)
    private val detailsBtn: Button = itemView.findViewById(R.id.details)
    private val deleteFavorite: Button = itemView.findViewById(R.id.delete_favorite)

    fun bind(film: Film, listener: FavoritesFilmsAdapter.FavoriteFilmClickListener) {
        deleteFavorite.visibility = View.VISIBLE
        deleteFavorite.setOnClickListener {
            listener.onDeleteFavoriteClick(film, adapterPosition)
        }

        image.setImageResource(film.image)
        title.text = film.title
        detailsBtn.setOnClickListener {
            listener.onDetailsClick(
                film,
                adapterPosition
            )
        }
    }
}