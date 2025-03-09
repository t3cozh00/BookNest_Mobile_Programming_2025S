package com.example.booknestapp.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
        ?: "https://plus.unsplash.com/premium_photo-1677526779324-01d20d3a27ef?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NDF8fGJvb2slMjBjb3ZlciUyMHByb2dyYW1taW5nfGVufDB8MXwwfHx8MA%3D%3D"

    Log.d("BookItemView", "Loading Image URL: $coverUrl") // ✅ Debugging

    Card (
        modifier = Modifier
            .padding(8.dp)

            .height(80.dp)
            .clickable { navController.navigate("bookDetail/${book.id}") }
    ){
        Row (
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){

            Image(
                painter = rememberAsyncImagePainter(
                    model = coverUrl,
                    onSuccess = { Log.d("BookItemView", "Image Loaded Successfully") },
                    onError = { Log.e("BookItemView", "Image Failed to Load") }

                ),
                contentDescription = book.volumeInfo.title,
                modifier = Modifier
                    .height(70.dp)
                    .width(50.dp)
                    .fillMaxWidth()
            )


            Spacer(modifier = Modifier.width(16.dp)) //Space between image & title

            Text(
                text = book.volumeInfo.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
        }
    }
}