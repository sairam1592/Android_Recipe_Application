package com.example.myapplication.arunproject.presentation.view.compose.recipeitem

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.arun.myapplication.R
import com.example.myapplication.arunproject.data.model.Recipe

/**
 * RecipeItem composable is used to display the recipe item in the list
 * @param recipe: Recipe
 */
@Composable
fun RecipeItem(recipe: Recipe, onClick: (String) -> Unit, isSelected: Boolean) {

    val backgroundColor = if (isSelected) {
        colorResource(id = R.color.blue_primary)
    } else {
        colorResource(id = R.color.blue_light)
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_12)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(id = R.dimen.padding_20),
                end = dimensionResource(id = R.dimen.padding_20),
                top = dimensionResource(id = R.dimen.padding_10),
                bottom = dimensionResource(id = R.dimen.padding_10)
            ),
        onClick = { onClick(recipe.id) }
    ) {
        Column {
            if (recipe.image.isEmpty()) {
                Image(
                    painterResource(id = android.R.drawable.dialog_holo_dark_frame),
                    contentDescription = recipe.description,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(16f / 9f),
                    contentScale = ContentScale.Crop

                )
            } else {
                Image(
                    painter = rememberAsyncImagePainter(recipe.image),
                    contentDescription = recipe.description,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(16f / 9f)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_16)))

            Text(
                text = recipe.name,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.text_size_18).value.sp
                ),
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.padding_16),
                    end = dimensionResource(id = R.dimen.padding_16),
                    top = dimensionResource(id = R.dimen.padding_2)
                ),
                color = Color.Black
            )

            Text(
                text = recipe.headline,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = dimensionResource(id = R.dimen.text_size_16).value.sp),
                color = Color.Gray,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.padding_16),
                    end = dimensionResource(id = R.dimen.padding_16),
                    bottom = dimensionResource(id = R.dimen.padding_5)
                )
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewRecipeItem() {
    val dummyRecipe = Recipe(
        calories = "200 kcal",
        carbos = "20g",
        description = "A delicious fish recipe",
        difficulty = 1,
        fats = "5g",
        headline = "Tasty Fish",
        id = "1",
        image = "",
        name = "Crispy Fish Goujons",
        proteins = "10g",
        thumb = "",
        time = "30 min"
    )
    RecipeItem(recipe = dummyRecipe, onClick = {}, isSelected = false)
}