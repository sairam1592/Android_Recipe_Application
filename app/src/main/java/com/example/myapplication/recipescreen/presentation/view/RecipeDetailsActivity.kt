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

        lifecycleScope.launch {
            viewModel.recipeDetails.collect { result ->
                //TODO FOR NOW THE RESULT IS RETRIEVED
                //TODO HANDLE ERROR CASE
                //TODO POPULATE DUMMY DATA IN FIREBASE
                //FETCH AND SHOW IN COMPOSE SCREEN
            }
        }
    }

    companion object {
        //TODO Remove this usage and use Compose Navigator
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, RecipeDetailsActivity::class.java))
        }
    }
}