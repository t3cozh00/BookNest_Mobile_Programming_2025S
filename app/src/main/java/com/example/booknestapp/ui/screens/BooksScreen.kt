package com.example.booknestapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.booknestapp.R
import com.example.booknestapp.ui.components.BookItemView
import com.example.booknestapp.ui.topbars.MainTopBar
import com.example.booknestapp.viewmodels.BooksViewModel

@Composable
fun BooksScreen(navController: NavController, viewModel: BooksViewModel= viewModel()) {
    val books by viewModel.books.collectAsState()
    val errorMessageResId by viewModel.errorMessageResId.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val currentPage by viewModel.currentPage.collectAsState()

    Scaffold(
        topBar = { MainTopBar(stringResource(R.string.booknest_topbar), navController) },
        containerColor = MaterialTheme.colorScheme.onPrimary
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                when {
                    isLoading -> {
                        LoadingScreen()
                    }

                    errorMessageResId != null -> {
                        ErrorScreen(stringResource(errorMessageResId!!), onRetry = { viewModel.fetchKotlinBooks() })
                    }

                    books.isEmpty() -> {
                        Text(text = stringResource(R.string.no_books_found), modifier = Modifier.align(Alignment.Center))
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {
                            items(books) { book ->
                                BookItemView(book, navController)
                            }
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Button(
                    onClick = { viewModel.previousPage() },
                    enabled = currentPage > 1
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(
                        R.string.previous_page
                    )
                    )
                }
                Text(text = stringResource(R.string.page_dots))
                Button(
                    onClick = { viewModel.nextPage() }
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = stringResource(
                        R.string.next_page
                    )
                    )
                }
            }
        }
    }
}