package com.example.rasoighar

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
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

class MealPlannerActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.mealplanner)
        val plansend = findViewById<Button>(R.id.Plan)
        val api = RetrofitClient.Client().create(ApiServiceKit::class.java)
        plansend.setOnClickListener{
            val age = findViewById<EditText>(R.id.age)
            val height = findViewById<EditText>(R.id.height)
            val weight = findViewById<EditText>(R.id.weight)
            val days = findViewById<EditText>(R.id.days)
            val veg = findViewById<CheckBox>(R.id.veg);
            val nonVeg = findViewById<CheckBox>(R.id.nonveg);
            val glufree = findViewById<CheckBox>(R.id.glfree);

            var selectedPreferences = ""

            if (veg.isChecked()) {
                selectedPreferences = "Vegetarian\n";
            }
            if (nonVeg.isChecked()) {
                selectedPreferences = "Non-Vegetarian\n";
            }
            if (glufree.isChecked()) {
                selectedPreferences = "Gluten-Free\n";
            }
            val genderRadioGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)
            val selectedGenderId: Int = genderRadioGroup.getCheckedRadioButtonId()
            val selectedGenderRadioButton = findViewById<RadioButton>(selectedGenderId)
            val gender = selectedGenderRadioButton?.text?.toString() ?: "No gender selected"

            // Gather the selected value from Diet Type RadioGroup
            val dietTypeRadioGroup = findViewById<RadioGroup>(R.id.dietTypeRadioGroup)
            // Gather the selected value from Diet Type RadioGroup
            val selectedDietTypeId: Int = dietTypeRadioGroup.getCheckedRadioButtonId()
            val selectedDietTypeRadioButton = findViewById<RadioButton>(selectedDietTypeId)
            val dietType = selectedDietTypeRadioButton?.text?.toString() ?: "No diet type selected"

            // Gather the selected value from Activity Level RadioGroup
            val activityLevelRadioGroup = findViewById<RadioGroup>(R.id.activityLevelRadioGroup)
            // Gather the selected value from Activity Level RadioGroup
            val selectedActivityLevelId: Int = activityLevelRadioGroup.getCheckedRadioButtonId()
            val selectedActivityLevelRadioButton =
                findViewById<RadioButton>(selectedActivityLevelId)
            val activityLevel = selectedActivityLevelRadioButton?.text?.toString() ?: "No activity level selected"

            val age_final = age.text.toString()
            val height_final = height.text.toString()
            val weight_final = weight.text.toString()
            val days_final = days.text.toString()
            val healthIssues = findViewById<EditText>(R.id.healthIssues).text.toString()
            val mealfreq = findViewById<EditText>(R.id.mealFreq).text.toString()
            val allergies = findViewById<EditText>(R.id.allergies).text.toString()

            lifecycleScope.launch {
                val result = withContext(Dispatchers.IO){
                    api.getPlan(MealPlan(
                        age = age_final,
                        height = height_final,
                        weight = weight_final,
                        days = days_final,
                        food_preference = selectedPreferences,
                        gender = gender,
                        diet_type = dietType,
                        allergies = allergies,
                        diseases = healthIssues,
                        activity_level = activityLevel,
                        meal_frequency = mealfreq
                    ))
                }
                withContext(Dispatchers.Main){
                    if(result.isSuccessful && result.body() != null){
                        val scroll = findViewById<ScrollView>(R.id.Scrollview)
                        scroll.visibility = View.GONE
                        val resultFinal = result.body().toString()
                        val result_scroll = findViewById<ScrollView>(R.id.result_scroll)
                        result_scroll.visibility = View.VISIBLE
                        val result_view = findViewById<TextView>(R.id.result_view)
                        result_view.text = result.body()?.meal_plan.toString()
                        Log.d("result",resultFinal)
                    }else{
                        Toast.makeText(this@MealPlannerActivity,"Some API Error",Toast.LENGTH_LONG).show()
                    }
                }
            }

        }
    }
}