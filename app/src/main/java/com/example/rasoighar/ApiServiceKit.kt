package com.example.rasoighar

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServiceKit {
    @POST("generate_meal_plan")
    suspend fun getPlan(@Body planRequest: MealPlan): retrofit2.Response<responseMeal>
}
interface Cultural_Exploration {
    @POST("explore_cuisine")
    suspend fun culexpl(@Body request: culexp): retrofit2.Response<culexpresponse>

}
interface eventPlanner{
    @POST("event_food_planning")
    suspend fun planevent(@Body newRequest: eventiten): retrofit2.Response<responseiten>
}
interface recipe_finder {
    @POST("find_recipe")
    suspend fun recipe_find(@Body new_request: recipe): retrofit2.Response<recipe_return>

}
interface Calcounter{
    @POST("estimate_nutrition")
    suspend fun calcount(@Body request_cal: cal): retrofit2.Response<response_cal>
}