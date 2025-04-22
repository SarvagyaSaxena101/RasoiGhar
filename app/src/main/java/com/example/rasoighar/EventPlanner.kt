package com.example.rasoighar

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.create

class EventPlanner: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.event_planner)
        val api = RetrofitClient.Client().create(eventPlanner::class.java)
        val plan = findViewById<Button>(R.id.Plan)
        val numberOfPeopleEditText = findViewById<EditText>(R.id.editTextNumber6)
        val cuisineEditText = findViewById<EditText>(R.id.editTextText4)

        val vegCheckBox = findViewById<CheckBox>(R.id.Event_veg)
        val nonVegCheckBox = findViewById<CheckBox>(R.id.Event_non_veg)
        val veganCheckBox = findViewById<CheckBox>(R.id.Event_vegan)
        val glutenFreeCheckBox = findViewById<CheckBox>(R.id.Event_gluten_free)

        val breakfastCheckBox = findViewById<CheckBox>(R.id.breakfast)
        val brunchCheckBox = findViewById<CheckBox>(R.id.brunch)
        val lunchCheckBox = findViewById<CheckBox>(R.id.lunch)
        val eveningSnacksCheckBox = findViewById<CheckBox>(R.id.eve_snacks)
        val dinnerCheckBox = findViewById<CheckBox>(R.id.dinner)

        plan.setOnClickListener {
            val numberOfPeople = numberOfPeopleEditText.text.toString()
            val cuisine = cuisineEditText.text.toString()

            val foodPreferences = mutableListOf<String>()
            if (vegCheckBox.isChecked) foodPreferences.add("Vegetarian")
            if (nonVegCheckBox.isChecked) foodPreferences.add("Non-Vegetarian")
            if (veganCheckBox.isChecked) foodPreferences.add("Vegan")
            if (glutenFreeCheckBox.isChecked) foodPreferences.add("Gluten-Free")

            val mealTypes = mutableListOf<String>()
            if (breakfastCheckBox.isChecked) mealTypes.add("Breakfast")
            if (brunchCheckBox.isChecked) mealTypes.add("Brunch")
            if (lunchCheckBox.isChecked) mealTypes.add("Lunch")
            if (eveningSnacksCheckBox.isChecked) mealTypes.add("Evening Snacks")
            if (dinnerCheckBox.isChecked) mealTypes.add("Dinner")

            val mealtype = mealTypes.toString()
            val foodpreference = foodPreferences.toString()


            lifecycleScope.launch {
                val result = withContext(Dispatchers.IO) {
                    api.planevent(
                        eventiten(
                            invitees = numberOfPeople, cuisine_type = cuisine,
                            food_preference = foodpreference,
                            meal_types = mealtype,
                        )
                    )
                }
                val scrollevent = findViewById<ScrollView>(R.id.scrollevent)
                scrollevent.visibility = View.GONE
                val scrolleresult = findViewById<ScrollView>(R.id.scrolle)
                scrolleresult.visibility = View.VISIBLE
                val result_display = findViewById<TextView>(R.id.textView)
                withContext(Dispatchers.Main) {
                    if (result.isSuccessful && result.body() != null) {
                        result_display.text = result.body()?.plan.toString()
                    } else {
                        Toast.makeText(this@EventPlanner, "Some API Error", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

        }
    }
}