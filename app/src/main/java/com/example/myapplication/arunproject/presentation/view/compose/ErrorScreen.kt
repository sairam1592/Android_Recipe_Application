package com.example.myapplication.arunproject.presentation.view.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.arun.myapplication.R
import com.example.myapplication.arunproject.common.AppConstants

/**
 * Show error screen
 */
@Composable
fun ErrorScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_16)),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                AppConstants.ERROR_MESSAGE,
                color = Color.Red,
                fontSize = dimensionResource(id = R.dimen.text_size_18).value.sp
            )
        }
    }
}
