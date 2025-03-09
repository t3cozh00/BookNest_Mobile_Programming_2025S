package com.example.booknestapp.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.booknestapp.models.BookItem

@Composable
fun BookItemView(book: BookItem, navController: NavController ){


    val coverUrl = book.volumeInfo.imageLinks?.thumbnail
            ?.replace("http://", "https://")
        ?: book.volumeInfo.imageLinks?.smallThumbnail
            ?.replace("http://", "https://")
        ?: "https://via.placeholder.com/150"

    Log.d("BookItemView", "Loading Image URL: $coverUrl") // ✅ Debugging

    Card (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(250.dp)
            .clickable { navController.navigate("bookDetail/${book.id}") }
    ){
        Column (
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Image(
                painter = rememberAsyncImagePainter(
                    model = coverUrl,
                    onSuccess = { Log.d("BookItemView", "Image Loaded Successfully") },
                    onError = { Log.e("BookItemView", "Image Failed to Load") }

                ),
                contentDescription = book.volumeInfo.title,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(8.dp))
            Text(
                book.volumeInfo.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}