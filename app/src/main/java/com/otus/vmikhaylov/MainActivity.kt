package com.otus.vmikhaylov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var selectedFilmNameID = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.film_1_details).setOnClickListener{
            if (selectedFilmNameID != 0){
                findViewById<TextView>(selectedFilmNameID).setTextColor(getColor(R.color.black))
            }
            selectedFilmNameID = R.string.film_1_name
            findViewById<TextView>(R.id.film_1_name).setTextColor(getColor(R.color.purple_200))
            val intent = Intent(this, Details::class.java)
            intent.putExtra("image", R.drawable.film_1)
            intent.putExtra("description", "Film 1 description")
            startActivity(intent)
        }

        findViewById<Button>(R.id.share_with_friend).setOnClickListener{
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                val film = if (selectedFilmNameID != 0) {
                    findViewById<TextView>(selectedFilmNameID).text
                } else ""
                putExtra(Intent.EXTRA_TEXT,
                    "Lets go to $film")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("selected_film_name_id", selectedFilmNameID)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        selectedFilmNameID = savedInstanceState.getInt("selected_film_name_id", 0) ?: 0
    }
}