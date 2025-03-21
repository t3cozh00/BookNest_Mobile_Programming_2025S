package com.example.booknestapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.booknestapp.R
import com.example.booknestapp.models.BookItem
import com.example.booknestapp.ui.topbars.ScreenTopBar

@Composable
fun BulletPointText(label: String, value: String) {
    Row {
        Spacer(modifier = Modifier.width(8.dp)) // Space between bullet and text
        Text(
            text = "○ $label: $value",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface)
        )
    }
}

@Composable
fun DetailScreen(navController: NavController, book: BookItem?){
    if (book == null){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(
                text = stringResource(R.string.book_not_found),
                style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.error)
            )
        }
        return
    }

    val httpString = stringResource(R.string.http)
    val httpsString = stringResource(R.string.https)

    val coverUrl = book.volumeInfo.imageLinks?.thumbnail
            ?.replace(httpString, httpsString)
        ?: book.volumeInfo.imageLinks?.smallThumbnail
            ?.replace(httpString, httpsString)
        ?: stringResource(R.string.book_placeholder_cover)

    Scaffold (
        topBar = { ScreenTopBar(stringResource(R.string.book_detail_topbar), navController) },
        containerColor = MaterialTheme.colorScheme.onPrimary
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ){
            Image(
                painter = rememberAsyncImagePainter(coverUrl),
                contentDescription = book.volumeInfo.title,
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = book.volumeInfo.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.height(4.dp))

            book.volumeInfo.subtitle?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.secondary)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            book.volumeInfo.authors?.let {
                BulletPointText(stringResource(R.string.author_s), it.joinToString())
            }
            book.volumeInfo.publisher?.let {
                BulletPointText(stringResource(R.string.publisher), it)
            }
            book.volumeInfo.publishedDate?.let {
                BulletPointText(stringResource(R.string.published), it)
            }
            Spacer(modifier = Modifier.height(8.dp))

            Card(
                shape = RoundedCornerShape(6.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Text(
                    text = book.volumeInfo.description ?: stringResource(R.string.no_description_available),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    modifier = Modifier.padding(8.dp)
                )
            }

        }

    }
}