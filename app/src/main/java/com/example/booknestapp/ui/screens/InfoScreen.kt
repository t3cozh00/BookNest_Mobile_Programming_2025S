package com.example.booknestapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.booknestapp.ui.topbars.ScreenTopBar


@Composable
fun InfoScreen(navController: NavController){
    Scaffold(
        topBar = { ScreenTopBar("Info", navController) },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                //horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp),

            ) {
                Text(text = "BookNest \uD83D\uDCDA", fontSize = 40.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "An app for discovering books about Kotlin.", fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Powered by Google Books API", fontSize = 24.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Version: 1.0.0", fontSize = 24.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Developer: Name", fontSize = 24.sp)
            }
        }
    }
}