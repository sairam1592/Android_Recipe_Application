package com.example.myapplication.arunproject.presentation.view.compose

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.arunproject.data.model.Recipe

@Composable
fun RecipeItem(recipe: Recipe) {
    Card(
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
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

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = recipe.name,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp),
                color = Color.Black
            )

            Text(
                text = recipe.headline,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
            )
        }
    }
}