package com.example.booknestapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import coil.compose.rememberAsyncImagePainter
import com.example.booknestapp.models.BookItem
import com.example.booknestapp.ui.topbars.ScreenTopBar

@Composable
fun DetailScreen(navController: NavController, book: BookItem?){
    if (book == null){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text("Book not found", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        return
    }

    val coverUrl = book.volumeInfo.imageLinks?.thumbnail
            ?.replace("http://", "https://")
        ?: book.volumeInfo.imageLinks?.smallThumbnail
            ?.replace("http://", "https://")
        ?: "https://via.placeholder.com/150"

    Scaffold (topBar = { ScreenTopBar("Book Detail", navController) },
        ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ){
            Image(
                painter = rememberAsyncImagePainter(coverUrl),
                contentDescription = book.volumeInfo.title,
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = book.volumeInfo.title, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            book.volumeInfo.subtitle?.let {
                Text(text = it)
            }
            Spacer(modifier = Modifier.height(16.dp))
            book.volumeInfo.authors?.let {
                Text(text = "Authors: ${it.joinToString()}")
            }
            book.volumeInfo.publisher?.let {
                Text(text = "Publisher: $it")
            }
            book.volumeInfo.publishedDate?.let {
                Text(text = "Published: $it")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = book.volumeInfo.description ?: "No description available.")

        }

    }
}