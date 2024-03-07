package com.example.myapplication.arunproject.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.arunproject.common.AppConstants

@Entity(tableName = AppConstants.TABLE_NAME)
data class RecipeEntity(
    @PrimaryKey val id: String,
    val calories: String,
    val carbos: String,
    val description: String,
    val difficulty: Int,
    val fats: String,
    val headline: String,
    val image: String,
    val name: String,
    val proteins: String,
    val thumb: String,
    val time: String
)
