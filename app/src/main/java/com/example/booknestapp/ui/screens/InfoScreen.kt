package com.example.booknestapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.booknestapp.R
import com.example.booknestapp.ui.topbars.ScreenTopBar

@Composable
fun InfoText(value: String) {
    Row {
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "- $value",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurface)
        )
    }
}

@Composable
fun InfoScreen(navController: NavController){
    Scaffold(
        topBar = { ScreenTopBar(stringResource(R.string.info_topbar), navController) },
        containerColor = MaterialTheme.colorScheme.onPrimary
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)

            ) {
                Text(text = stringResource(R.string.booknest_emoji),
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))

                InfoText(stringResource(R.string.an_app_for_discovering_books))
                InfoText(stringResource(R.string.powered_by_google_books_api))
                InfoText(stringResource(R.string.version_1_0_0))
                InfoText(stringResource(R.string.developer_name))
                InfoText(stringResource(R.string.date_10_03_2025))

            }
        }
    }
}