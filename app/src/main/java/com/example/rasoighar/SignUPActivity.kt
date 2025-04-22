package com.example.rasoighar

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SignUPActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.signup)
        val su = findViewById<ImageButton>(R.id.signup_button)
        val sn = findViewById<ImageButton>(R.id.signin_page)
        sn.setOnClickListener {
            Intent(this, SigninActivity::class.java).also{
                startActivity(it)
            }
        }
    }


}