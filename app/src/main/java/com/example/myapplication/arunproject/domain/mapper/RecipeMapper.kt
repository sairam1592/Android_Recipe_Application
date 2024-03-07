package com.example.myapplication.arunproject.domain.mapper

import com.example.myapplication.arunproject.data.model.Recipe
import com.example.myapplication.arunproject.data.model.db.RecipeEntity

object RecipeMapper {
    fun entityToDomain(entity: RecipeEntity): Recipe {
        return Recipe(
            id = entity.id,
            name = entity.name,
            calories = entity.calories,
            carbos = entity.carbos,
            description = entity.description,
            difficulty = entity.difficulty,
            fats = entity.fats,
            headline = entity.headline,
            image = entity.image,
            proteins = entity.proteins,
            thumb = entity.thumb,
            time = entity.time
        )
    }

    fun domainToEntity(recipe: Recipe): RecipeEntity {
        return RecipeEntity(
            id = recipe.id,
            name = recipe.name,
            calories = recipe.calories,
            carbos = recipe.carbos,
            description = recipe.description,
            difficulty = recipe.difficulty,
            fats = recipe.fats,
            headline = recipe.headline,
            image = recipe.image,
            proteins = recipe.proteins,
            thumb = recipe.thumb,
            time = recipe.time
        )
    }
}