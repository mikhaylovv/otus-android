package com.otus.vmikhaylov

import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.title)
    private val image: ImageView = itemView.findViewById(R.id.image)
    private val detailsBtn: Button = itemView.findViewById(R.id.details)
    private val favorite: ImageButton = itemView.findViewById(R.id.favorite)
    private val favoriteMarker: ImageView = itemView.findViewById(R.id.favorite_marker)

    fun bind(film: Film, listener: FilmsAdapter.FilmClickListener) {
        image.setImageResource(film.image)
        title.text = film.title
        favorite.setOnClickListener {
            listener.onFavoriteClick(
                film,
                adapterPosition
            )
        }
        detailsBtn.setOnClickListener {
            listener.onDetailsClick(
                film,
                adapterPosition
            )
        }
        if (film.selected) {
            title.setTextColor(Color.parseColor("#FFBB86FC"))
        }
        setFavorite(film.favorite)

        favoriteMarker.setOnClickListener {
            it.visibility = View.GONE
            film.favorite = false
        }
    }

    fun setFavorite(v: Boolean) {
        favoriteMarker.visibility = if (v) View.VISIBLE  else View.GONE
    }
}