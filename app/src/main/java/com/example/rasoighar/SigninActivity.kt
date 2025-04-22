package com.example.rasoighar

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SigninActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.siginin)

        val signin = findViewById<ImageButton>(R.id.signin_button)
        signin.setOnClickListener {
            Intent(this, MainPageActivity::class.java).also{
                startActivity(it)
            }
        }

    }


}