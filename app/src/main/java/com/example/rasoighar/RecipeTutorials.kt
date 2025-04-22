package com.example.rasoighar

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeTutorials: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.recipe_tutorial_page_xml)
        val searchbutton = findViewById<ImageButton>(R.id.srchbutton)
        val dish = findViewById<EditText>(R.id.dish)
        val api = RetrofitClient.Client().create(recipe_finder::class.java)


        searchbutton.setOnClickListener {
            lifecycleScope.launch {
                val result = withContext(Dispatchers.Default) {
                    api.recipe_find(recipe(dish.text.toString()))
                }
                val show = findViewById<TextView>(R.id.scrolleview_recipe_result)
                withContext(Dispatchers.Main) {
                    if (result.isSuccessful && result.body() != null) {
                        show.text = result.body().toString()
                    }else{
                        Toast.makeText(this@RecipeTutorials,"Some API Error", Toast.LENGTH_LONG).show()

                    }
                }
            }
        }
    }
}