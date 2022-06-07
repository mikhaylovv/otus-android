package com.otus.vmikhaylov

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.otus.vmikhaylov.favorites.FavoritesActivity

class MainActivity : AppCompatActivity() {
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler) }

    var films = mutableListOf<Film>()
    var favorites = mutableListOf<Film>()

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

        val startFavoritesActivity = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val data = result.data
            if (result.resultCode == RESULT_OK && data != null) {
                favorites = data.getParcelableArrayListExtra("films") ?: favorites
            }
        }

        findViewById<Button>(R.id.favorites).setOnClickListener {
            val intent = Intent(this@MainActivity, FavoritesActivity::class.java)
            intent.putExtra("films", ArrayList(favorites))
            startFavoritesActivity.launch(intent)
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
        recyclerView.layoutManager = GridLayoutManager(this, resources.configuration.orientation)
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
                if (!favorites.contains(film)){
                    favorites.add(film)
                }
                Toast.makeText(this@MainActivity, "Favorite Click", Toast.LENGTH_SHORT).show()
            }
        })

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.black_line_5dp, theme)
            ?.let { divider.setDrawable(it) }
        recyclerView.addItemDecoration(divider)
    }

    override fun onBackPressed() {
        ExitDialog{ super.onBackPressed() }.show(supportFragmentManager, "exit_dialog")
    }
}