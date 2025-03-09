package com.example.booknestapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.booknestapp.R


@Composable
fun ErrorScreen(errorMessage: String, onRetry: () -> Unit){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.oops_something_went_wrong),
                fontSize = 30.sp,
            )
            Text(text=errorMessage)
            Button(onClick = onRetry) {
                Text(text = stringResource(R.string.retry))
            }
        }
    }
}