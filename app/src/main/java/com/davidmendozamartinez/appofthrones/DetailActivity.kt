package com.davidmendozamartinez.appofthrones

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {
            Toast.makeText(this@DetailActivity, "Winter is coming", Toast.LENGTH_SHORT).show()
        }
    }
}
