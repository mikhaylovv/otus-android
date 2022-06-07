package com.otus.vmikhaylov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otus.vmikhaylov.favorites.FavoritesActivity
import com.otus.vmikhaylov.favorites.FavoritesFilmsAdapter

class MainActivity : AppCompatActivity() {
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler) }

    var films = mutableListOf<Film>()
    var favourites = mutableListOf<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        films = mutableListOf(
            Film(R.drawable.film_1, getString(R.string.film_1_name), getString(R.string.film_1_desc), false),
            Film(R.drawable.film_2, getString(R.string.film_2_name), getString(R.string.film_2_desc), false),
            Film(R.drawable.film_3, getString(R.string.film_3_name), getString(R.string.film_3_desc), false),
            Film(R.drawable.film_4, getString(R.string.film_4_name), getString(R.string.film_4_desc), false),
        )

        findViewById<Button>(R.id.share_with_friend).setOnClickListener{
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT,
                    "Lets go to to the cinema")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        findViewById<Button>(R.id.favorites).setOnClickListener {
            val intent = Intent(this@MainActivity, FavoritesActivity::class.java)
            intent.putExtra("films", ArrayList(favourites))
            startActivity(intent)
        }

        initRecycler()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("films", ArrayList(films))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val bundleFilms = savedInstanceState.getParcelableArrayList<Film>("selected_films") ?: return
        films = bundleFilms
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = FilmsAdapter(films, object : FilmsAdapter.FilmClickListener {
            override fun onDetailsClick(film: Film, position: Int) {
                val view = recyclerView.getChildAt(position)
                view.findViewById<TextView>(R.id.title).setTextColor(getColor(R.color.purple_200))

                val intent = Intent(this@MainActivity, Details::class.java)
                intent.putExtra("image", film.image)
                intent.putExtra("description", film.description)
                startActivity(intent)

                films[position].selected = true

                Toast.makeText(this@MainActivity, "News Click", Toast.LENGTH_SHORT).show()
            }

            override fun onFavoriteClick(film: Film, position: Int) {
                favourites.add(film)
                Toast.makeText(this@MainActivity, "Favorite Click", Toast.LENGTH_SHORT).show()
            }
        })

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.black_line_5dp, theme)
            ?.let { divider.setDrawable(it) }
        recyclerView.addItemDecoration(divider)
    }
}