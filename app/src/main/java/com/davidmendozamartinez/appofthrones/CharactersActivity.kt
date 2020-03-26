package com.davidmendozamartinez.appofthrones

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_characters.*

class CharactersActivity : AppCompatActivity(), CharactersFragment.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        if (savedInstanceState == null) {
            val fragment = CharactersFragment()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.listContainer, fragment)
                .commit()
        }
    }

    override fun onItemClicked(character: Character) {
        if (isDetailViewAvailable())
            showDetailFragment(character.id)
        else
            launchDetailActivity(character.id)
    }

    private fun isDetailViewAvailable() = detailContainer != null

    private fun showDetailFragment(characterId: String) {
        val detailFragment = DetailFragment.newInstance(characterId)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.detailContainer, detailFragment)
            .commit()
    }

    private fun launchDetailActivity(characterId: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("key_id", characterId)
        startActivity(intent)
    }
}