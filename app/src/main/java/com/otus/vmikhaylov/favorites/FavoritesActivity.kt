package com.otus.vmikhaylov.favorites

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otus.vmikhaylov.Details
import com.otus.vmikhaylov.Film
import com.otus.vmikhaylov.R

class FavoritesActivity : AppCompatActivity() {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.favorites_recycler) }

    private var films = mutableListOf<Film>()
    private var favoritesFilms = mutableListOf<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        films = intent.getParcelableArrayListExtra("films") ?: mutableListOf()
        favoritesFilms = films.filter { it.favorite } as MutableList<Film>

        recyclerView.layoutManager = GridLayoutManager(this, resources.configuration.orientation)
        recyclerView.adapter = FavoritesFilmsAdapter(
            favoritesFilms,
            object : FavoritesFilmsAdapter.FavoriteFilmClickListener {
                override fun onDetailsClick(film: Film, position: Int) {
                    val intent = Intent(this@FavoritesActivity, Details::class.java)
                    intent.putExtra("image", film.image)
                    intent.putExtra("description", film.description)
                    startActivity(intent)
                }

                override fun onDeleteFavoriteClick(film: Film, position: Int) {
                    films.find { f -> film.title == f.title  }?.apply {
                        favorite = false
                    }
                    favoritesFilms.removeAt(position)
                    recyclerView.adapter?.notifyItemRemoved(position)
                }
            }
        )

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.black_line_5dp, theme)
            ?.let { divider.setDrawable(it) }
        recyclerView.addItemDecoration(divider)
    }

    override fun onBackPressed() {
        for (film in films) {
            if (film.favorite) {
                val favoriteFilm = favoritesFilms.find { favoriteFilm -> favoriteFilm.title == film.title }
                film.favorite = favoriteFilm != null
            }
        }

        val data = Intent()
        data.putExtra("films", ArrayList(films))
        setResult(RESULT_OK, data)

        super.onBackPressed()
    }
}