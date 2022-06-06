package com.otus.vmikhaylov

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTv: TextView = itemView.findViewById(R.id.title)
    private val imageIv: ImageView = itemView.findViewById(R.id.image)

    fun bind(film: Film, listener: FilmsAdapter.FilmClickListener) {
        imageIv.setImageResource(film.image)
        titleTv.text = film.title
        imageIv.setOnClickListener {
            listener.onFavoriteClick(
                film,
                adapterPosition
            )
        }
        itemView.setOnClickListener {
            listener.onNewsClick(
                film,
                adapterPosition
            )
        }
    }
}