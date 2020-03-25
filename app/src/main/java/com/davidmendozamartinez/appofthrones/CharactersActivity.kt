package com.davidmendozamartinez.appofthrones

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CharactersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        val list: RecyclerView = findViewById(R.id.list)
        val adapter = CharactersAdapter()

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        val characters: MutableList<Character> = CharactersRepository.characters
        adapter.setCharacters(characters)
    }
}
