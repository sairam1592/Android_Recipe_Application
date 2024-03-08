package com.example.myapplication.arunproject.presentation.view.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.arun.myrecipeapplication.R
import com.example.myapplication.arunproject.common.AppConstants

@Composable
fun BottomSheetContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_20))) {
            Text(
                text = AppConstants.MY_INTRO,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.text_size_18).value.sp,
                ),
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.padding_16),
                    end = dimensionResource(id = R.dimen.padding_16),
                    top = dimensionResource(id = R.dimen.padding_2),
                ),
                color = colorResource(id = R.color.purple_700),
            )
        }
    }
}