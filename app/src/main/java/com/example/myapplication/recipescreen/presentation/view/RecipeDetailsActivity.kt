package com.example.myapplication.recipescreen.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.recipescreen.presentation.viewmodel.RecipeDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipeDetailsActivity : AppCompatActivity() {
    private val viewModel: RecipeDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recipeId = intent.getStringExtra(EXTRA_RECIPE_ID)

        recipeId?.let { viewModel.getRecipeDetails(it) }

        lifecycleScope.launch {
            viewModel.recipeDetails.collect { result -> //Returns a list of questions to be displayed
                //TODO HANDLE ERROR CASE
                //TODO FETCH normal details from DB AND SHOW IN COMPOSE SCREEN
            }
        }
    }

    companion object {

        const val EXTRA_RECIPE_ID = "extra_recipe_id"

        //TODO Remove this usage and use Compose Navigator
        fun startActivity(context: Context, recipeId: String) {
            val intent = Intent(context, RecipeDetailsActivity::class.java).apply {
                putExtra(EXTRA_RECIPE_ID, recipeId)
            }
            context.startActivity(intent)
        }
    }
}