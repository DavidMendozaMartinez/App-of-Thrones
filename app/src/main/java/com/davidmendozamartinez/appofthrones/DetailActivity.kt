package com.davidmendozamartinez.appofthrones

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent.getStringExtra("key_id")?.let { id ->
            if (savedInstanceState == null) {
                val fragment = DetailFragment.newInstance(id)

                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.detailContainer, fragment)
                    .commit()
            }
        } ?: throw IllegalAccessException("Parent activity doesn't add key_id data to the intent")
    }
}