package com.example.myapplication.arunproject.presentation.view.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.arun.myapplication.R
import com.example.myapplication.arunproject.common.AppConstants
import com.example.myapplication.arunproject.common.AppConstants.RETRY

/**
 * Show error screen
 */
@Composable
fun ErrorScreen(onRetry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_20)),
        contentAlignment = Alignment.CenterStart
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = painterResource(id = R.drawable.ic_error_img),
                contentDescription = "",
                modifier = Modifier
                    .wrapContentSize(),
            )

            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_10)))

            Text(
                AppConstants.NO_INTERNET_ERROR_MESSAGE,
                color = colorResource(id = R.color.blue_primary),
                fontSize = dimensionResource(id = R.dimen.text_size_22).value.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_10)))

            Button(
                onClick = { onRetry() },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.blue_primary))
            ) {
                Text(
                    text = RETRY,
                    color = colorResource(id = R.color.white),
                )
            }
        }
    }
}
