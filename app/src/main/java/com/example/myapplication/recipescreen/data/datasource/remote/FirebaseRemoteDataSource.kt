package com.example.myapplication.recipescreen.data.datasource.remote

interface FirebaseRemoteDataSource {
    suspend fun getRecipeDetails(recipeId: String): List<String>
}