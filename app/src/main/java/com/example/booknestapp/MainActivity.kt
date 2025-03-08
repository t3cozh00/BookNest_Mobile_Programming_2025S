package com.example.booknestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
                val booksViewModel = BooksViewModel()
                ScaffoldApp(booksViewModel)
            }
        }
    }
}

@Composable
fun ScaffoldApp(booksViewModel: BooksViewModel){
    val navController = rememberNavController()
    val books by booksViewModel.books.collectAsState()

    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable(route = "home") { BooksScreen(navController, booksViewModel) }
        composable(route = "info") { InfoScreen(navController) }
        composable(route = "bookDetail/{bookId}") { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
            val selectedBook = books.find { it.id == bookId }

            DetailScreen(navController, selectedBook)
        }
    }
}