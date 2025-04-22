package com.example.rasoighar

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class CalorieCounter: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.caloriecounter)
        val dish = findViewById<EditText>(R.id.dish)
        val send = findViewById<ImageButton>(R.id.send)
        val tv = findViewById<TextView>(R.id.textView2)
        val home = findViewById<ImageButton>(R.id.home_button)
        val chat = findViewById<ImageButton>(R.id.chat_button)
        val api = RetrofitClient.Client().create(Calcounter::class.java)
        send.setOnClickListener {
            lifecycleScope.launch {
                val result = withContext(Dispatchers.Default){
                    api.calcount(cal(
                        dish_name = dish.text.toString(),
                        servings= "1"
                    ))
                }
                withContext(Dispatchers.Default){
                    if (result.isSuccessful && result.body() != null){
                        tv.text = result.body()?.nutrition_info.toString()
                    }else{
                        Toast.makeText(this@CalorieCounter, "Backend Error", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }
}