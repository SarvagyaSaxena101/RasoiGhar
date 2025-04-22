package com.example.rasoighar

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainPageActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.homepage)

        val calorie = findViewById<ImageButton>(R.id.calorie_counter)
        calorie.setOnClickListener {
            Intent(this, CalorieCounter::class.java).also{
                startActivity(it)
            }
        }
        val meal = findViewById<ImageButton>(R.id.meal_button)
        meal.setOnClickListener {
            Intent(this, MealPlannerActivity::class.java).also {
                startActivity(it)
            }
        }
        val fridge = findViewById<ImageButton>(R.id.food_fridge)
        fridge.setOnClickListener {
            Intent(this, FoodFromFridge::class.java).also {
                startActivity(it)
            }
        }
        val culture = findViewById<ImageButton>(R.id.cutural_exploration)
        culture.setOnClickListener {
            Intent(this, CultureExploration::class.java).also {
                startActivity(it)
            }
        }
        val event = findViewById<ImageButton>(R.id.event_planner)
        event.setOnClickListener {
            Intent(this, EventPlanner::class.java).also {
                startActivity(it)
            }
        }
        val recipe = findViewById<ImageButton>(R.id.recipe_tutorials)
        recipe.setOnClickListener {
            Intent(this, RecipeTutorials::class.java).also {
                startActivity(it)
            }
        }
        val detection = findViewById<ImageButton>(R.id.food_detection)
        detection.setOnClickListener {
            Intent(this, FoodDetection::class.java).also {
                startActivity(it)
            }
        }
        val home = findViewById<ImageButton>(R.id.home_button_main_page)
        home.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}