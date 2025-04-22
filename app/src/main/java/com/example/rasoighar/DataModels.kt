package com.example.rasoighar


data class MealPlan(
    val age: String,
    val height: String ,
    val weight: String,
    val days: String,
    val food_preference: String,
    val gender: String,
    val diet_type: String,
    val allergies: String,
    val diseases: String,
    val activity_level: String,
    val meal_frequency: String
)
data class responseMeal(
    val meal_plan : String
)

data class culexp(
    val region: String,
    val country: String
)
data class culexpresponse(
    val dish: String
)

data class eventiten(
    val invitees: String,
    val cuisine_type: String,
    val food_preference: String,
    val meal_types: String,

    )
data class responseiten(
    val plan: String
)
data class recipe(
    val recipe_name: String
)
data class  recipe_return(
    val recipe_name: String,
    val ingredients: String,
    val instructions: String,
    val youtube_tutorials: String
)

data class cal(
    val dish_name: String,
    val servings: String
)
data class response_cal(
    val nutrition_info: String
)