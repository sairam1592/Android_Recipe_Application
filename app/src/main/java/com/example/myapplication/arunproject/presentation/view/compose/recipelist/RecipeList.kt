package com.example.myapplication.arunproject.presentation.view.compose.recipelist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arun.myrecipeapplication.R
import com.example.myapplication.arunproject.common.AppConstants
import com.example.myapplication.arunproject.data.model.Recipe
import com.example.myapplication.arunproject.presentation.view.compose.recipeitem.RecipeItem
import com.example.myapplication.arunproject.presentation.view.compose.recipeitem.RecipeItemGrid
import com.example.myapplication.arunproject.presentation.view.compose.recipeitem.RecipeItemHorizontalGrid

@Composable
fun RecipeList(
    recipes: List<Recipe>,
    isShowGrid: Boolean = false,
    isShowGridAdaptive: Boolean = false,
    selectedRecipeId: String,
    onRecipeClick: (String) -> Unit
) {

    if (isShowGrid) {
        val columns = 2

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.padding_20)),
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_10))
        ) {

            items(recipes.size) { index ->
                if (index > 0) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_20)))
                }
                RecipeItemGrid(
                    recipe = recipes[index],
                    onClick = { recipeId -> onRecipeClick(recipeId) },
                    isSelected = recipes[index].id == selectedRecipeId
                )

                if (index == recipes.size - 1) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_20)))
                }
            }
        }
    } else if (isShowGridAdaptive) {
        val minSize = 150.dp

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize),
            contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.padding_20)),
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_10))
        ) {
            items(recipes.size) { index ->
                if (index > 0) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_20)))
                }
                RecipeItemGrid(
                    recipe = recipes[index],
                    onClick = { recipeId -> onRecipeClick(recipeId) },
                    isSelected = recipes[index].id == selectedRecipeId
                )

                if (index == recipes.size - 1) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_20)))
                }
            }
        }
    } else {
        LazyColumn {
            item {

                Column {

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_16)))

                    Text(
                        text = AppConstants.RECIPE_ROW,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = dimensionResource(id = R.dimen.text_size_22).value.sp
                        ),
                        modifier = Modifier.padding(
                            start = dimensionResource(id = R.dimen.padding_20),
                            end = dimensionResource(id = R.dimen.padding_20)
                        ),
                        color = colorResource(id = R.color.blue_primary)
                    )

                    Box(modifier = Modifier.height(150.dp)) {
                        LazyRow(
                            contentPadding = PaddingValues(
                                start = dimensionResource(id = R.dimen.padding_16),
                                top = dimensionResource(id = R.dimen.padding_12)
                            ),
                        ) {

                            itemsIndexed(recipes) { index, recipe ->
                                RecipeItemHorizontalGrid(
                                    recipe = recipe,
                                    onClick = { recipeId -> onRecipeClick(recipeId) },
                                    isSelected = recipe.id == selectedRecipeId,
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_16)))

                    Text(
                        text = AppConstants.RECIPE_COLUMN,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = dimensionResource(id = R.dimen.text_size_22).value.sp
                        ),
                        modifier = Modifier.padding(
                            start = dimensionResource(id = R.dimen.padding_20),
                            end = dimensionResource(id = R.dimen.padding_20)
                        ),
                        color = colorResource(id = R.color.blue_primary)
                    )

                }
            }

            items(recipes.size) { index ->
                if (index > 0) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_10)))
                }
                RecipeItem(
                    recipe = recipes[index],
                    onClick = { recipeId -> onRecipeClick(recipeId) },
                    isSelected = recipes[index].id == selectedRecipeId
                )

                if (index == recipes.size - 1) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_10)))
                }
            }
        }
    }
}