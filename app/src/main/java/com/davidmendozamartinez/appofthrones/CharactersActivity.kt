package com.davidmendozamartinez.appofthrones

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class CharactersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)
    }

    fun showDetails(button: View) {
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }
}
