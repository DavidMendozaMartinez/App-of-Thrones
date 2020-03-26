package com.davidmendozamartinez.appofthrones

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getStringExtra("key_id")
        val character = CharactersRepository.findCharacterById(id)

        character?.let {
            with(character) {
                labelName.text = name
                labelTitle.text = title
                labelBorn.text = born
                labelActor.text = actor
                labelQuote.text = quote
                labelParents.text = "$father & $mother"
                labelSpouse.text = spouse
                button.text = house.name
            }
        }

        button.setOnClickListener {
            Toast.makeText(this@DetailActivity, character?.house?.words, Toast.LENGTH_SHORT).show()
        }
    }
}