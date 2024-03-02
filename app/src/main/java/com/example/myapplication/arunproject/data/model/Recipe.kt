package com.example.myapplication.arunproject.data.model

/* This is a data class that represents a recipe.
    @param id The id of the recipe.
    @param name The name of the recipe.
    @param description The description of the recipe.
    @param imageUrl The image url of the recipe.
 */
data class Recipe(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String
)
