package com.otus.vmikhaylov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var selectedFilms = BooleanArray(4) {false}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun clickListener (img: Int, filmName: Int, description: Int) {
            findViewById<TextView>(filmName).setTextColor(getColor(R.color.purple_200))
            val intent = Intent(this, Details::class.java)
            intent.putExtra("image", img)
            intent.putExtra("description", getText(description))
            startActivity(intent)
        }

        findViewById<Button>(R.id.film_1_details).setOnClickListener{
            selectedFilms[0] = true
            clickListener(R.drawable.film_1, R.id.film_1_name, R.string.film_1_desc)
        }
        findViewById<Button>(R.id.film_2_details).setOnClickListener{
            selectedFilms[1] = true
            clickListener(R.drawable.film_2, R.id.film_2_name, R.string.film_2_desc)
        }
        findViewById<Button>(R.id.film_3_details).setOnClickListener{
            selectedFilms[2] = true
            clickListener(R.drawable.film_3, R.id.film_3_name, R.string.film_3_desc)
        }
        findViewById<Button>(R.id.film_4_details).setOnClickListener{
            selectedFilms[3] = true
            clickListener(R.drawable.film_4, R.id.film_4_name, R.string.film_4_desc)
        }

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
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBooleanArray("selected_film_name_id", selectedFilms)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        selectedFilms = savedInstanceState.getBooleanArray("selected_film_name_id") ?: BooleanArray(4) {false}

        val films = listOf(R.id.film_1_name, R.id.film_2_name, R.id.film_3_name, R.id.film_4_name)
        repeat(4) { i ->
            if (selectedFilms[i]) {
                findViewById<TextView>(films[i]).setTextColor(getColor(R.color.purple_200))
            }
        }
    }
}