package com.otus.vmikhaylov

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.title)
    private val image: ImageView = itemView.findViewById(R.id.image)
    private val detailsBtn: Button = itemView.findViewById(R.id.details)

    fun bind(film: Film, listener: FilmsAdapter.FilmClickListener) {
        image.setImageResource(film.image)
        title.text = film.title
        image.setOnClickListener {
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
    }
}