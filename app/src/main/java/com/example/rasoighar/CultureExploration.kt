package com.example.rasoighar

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.rasoighar.CalorieCounter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CultureExploration: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.cultural_exploration)
        val country = findViewById<EditText>(R.id.countryName)
        val city = findViewById<EditText>(R.id.cityName)
        val api = RetrofitClient.Client().create(Cultural_Exploration::class.java)
        val send = findViewById<ImageButton>(R.id.cultural_exploration_button)
        val tv = findViewById<TextView>(R.id.cultural_exploration_text)
        send.setOnClickListener {
            lifecycleScope.launch {
                val result = withContext (Dispatchers.Default){
                    api.culexpl(culexp(
                        region = country.text.toString(),
                        country = city.text.toString()
                    ))
                }
                withContext (Dispatchers.Default){
                    if(result.isSuccessful && result.body() != null){
                        tv.text = result.body()?.dish.toString()
                    }else{
                        Toast.makeText(this@CultureExploration, "Backend Error", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}