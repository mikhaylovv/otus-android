package com.otus.vmikhaylov

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var selectedFilms = BooleanArray(4) {false}

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler) }

    var films = mutableListOf<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        films = mutableListOf(
            Film(R.drawable.film_1, getString(R.string.film_1_name), getString(R.string.film_1_desc)),
            Film(R.drawable.film_2, getString(R.string.film_2_name), getString(R.string.film_2_desc)),
            Film(R.drawable.film_3, getString(R.string.film_3_name), getString(R.string.film_3_desc)),
            Film(R.drawable.film_4, getString(R.string.film_4_name), getString(R.string.film_4_desc)),
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

        initRecycler()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBooleanArray("selected_film_name_id", selectedFilms)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        selectedFilms = savedInstanceState.getBooleanArray("selected_film_name_id") ?: BooleanArray(4) {false}
//
//        val films = listOf(R.id.film_1_name, R.id.film_2_name, R.id.film_3_name, R.id.film_4_name)
//        repeat(4) { i ->
//            if (selectedFilms[i]) {
//                findViewById<TextView>(films[i]).setTextColor(getColor(R.color.purple_200))
//            }
//        }
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

                Toast.makeText(this@MainActivity, "News Click", Toast.LENGTH_SHORT).show()
            }

            override fun onFavoriteClick(film: Film, position: Int) {
                Toast.makeText(this@MainActivity, "Favorite Click", Toast.LENGTH_SHORT).show()
            }
        })

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.black_line_5dp, theme)
            ?.let { divider.setDrawable(it) }
        recyclerView.addItemDecoration(divider)
    }
}