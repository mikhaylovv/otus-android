package com.otus.vmikhaylov

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class Details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        intent.getIntExtra("image", 0).let { image ->
            findViewById<ImageView>(R.id.details_film_cover).setImageResource(image)
        }
        intent.getStringExtra("description").let { description ->
            findViewById<TextView>(R.id.details_film_description).text = description
        }
    }
}