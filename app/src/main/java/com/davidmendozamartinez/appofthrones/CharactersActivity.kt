package com.davidmendozamartinez.appofthrones

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CharactersActivity : AppCompatActivity(), CharactersFragment.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        val fragment = CharactersFragment()

        if (savedInstanceState == null)
            this.supportFragmentManager
                .beginTransaction()
                .add(R.id.listContainer, fragment)
                .commit()
    }

    override fun onItemClicked(character: Character) {
        showDetails(character.id)
    }

    private fun showDetails(characterId: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("key_id", characterId)
        startActivity(intent)
    }
}