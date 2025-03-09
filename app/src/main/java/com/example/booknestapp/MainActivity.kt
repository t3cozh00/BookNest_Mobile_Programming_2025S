package com.example.booknestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.booknestapp.ui.screens.BooksScreen
import com.example.booknestapp.ui.screens.DetailScreen
import com.example.booknestapp.ui.screens.InfoScreen
import com.example.booknestapp.ui.theme.BookNestAppTheme
import com.example.booknestapp.viewmodels.BooksViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookNestAppTheme {
                Surface (color = MaterialTheme.colorScheme.onPrimary) {
                    val booksViewModel = BooksViewModel()
                    ScaffoldApp(booksViewModel)
                }
            }
        }
    }
}

@Composable
fun ScaffoldApp(booksViewModel: BooksViewModel){
    val navController = rememberNavController()
    val books by booksViewModel.books.collectAsState()

    val homeRoute = stringResource(R.string.home)
    val infoRoute = stringResource(R.string.info)


    Surface (color = MaterialTheme.colorScheme.onPrimary) {
        NavHost(
            navController = navController,
            startDestination = homeRoute
        ) {
            composable(route = homeRoute) { BooksScreen(navController, booksViewModel) }
            composable(route = infoRoute) { InfoScreen(navController) }
            composable(route = "bookDetail/{bookId}") { backStackEntry ->
                val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
                val selectedBook = books.find { it.id == bookId }

                DetailScreen(navController, selectedBook)
            }

        }
    }
}