package com.example.myapplication.arunproject.data.network

import com.example.myapplication.arunproject.data.model.Recipe
import retrofit2.Response
import retrofit2.http.GET

/**
 * This is a service that provides access to recipes.
 */
interface RecipeService {
    @GET("recipes.json")
    suspend fun getRecipes(): Response<List<Recipe>>
}