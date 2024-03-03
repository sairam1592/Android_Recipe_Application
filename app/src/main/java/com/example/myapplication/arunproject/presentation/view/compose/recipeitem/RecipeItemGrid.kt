package com.example.myapplication.arunproject.presentation.view.compose.recipeitem

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.arun.myapplication.R
import com.example.myapplication.arunproject.data.model.Recipe

@Composable
fun RecipeItemGrid(recipe: Recipe, onClick: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.blue_light)),
            elevation = CardDefaults.cardElevation(),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_8)),
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_8))
                .aspectRatio(1f)
            , onClick = { onClick(recipe.id) }
        ) {
            Image(
                painter = if (recipe.image.isEmpty()) {
                    painterResource(id = android.R.drawable.dialog_holo_dark_frame)
                } else {
                    rememberAsyncImagePainter(recipe.image)
                },
                contentDescription = recipe.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = recipe.name,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(id = R.dimen.padding_8),
                    vertical = dimensionResource(id = R.dimen.padding_4)
                )
                .fillMaxWidth(),
            color = Color.Black,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewRecipeItemGrid() {
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
    RecipeItemGrid(recipe = dummyRecipe, onClick = { })
}