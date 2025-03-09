package com.example.booknestapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booknestapp.BuildConfig
import com.example.booknestapp.R
import com.example.booknestapp.api.BooksApi
import com.example.booknestapp.models.BookItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {
    private  val _books = MutableStateFlow<List<BookItem>>(emptyList())
    val books: StateFlow<List<BookItem>> get() = _books

    private val _errorMessageResId = MutableStateFlow<Int?>(null) // Store resource ID instead
    val errorMessageResId: StateFlow<Int?> get() = _errorMessageResId

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _currentPage = MutableStateFlow(1)
    val currentPage: StateFlow<Int> get() = _currentPage

    private val apiKey = BuildConfig.SECURE_API_KEY

    private val maxResults = 9 //Number of books per page

    init {
        fetchKotlinBooks()
    }

    fun fetchKotlinBooks(page: Int = _currentPage.value){
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessageResId.value = null

            try {
                val startIndex = (page - 1) * maxResults //Calculate correct start index
                val bookList = BooksApi.getInstance().getKotlinBooks(
                    apiKey = apiKey,
                    startIndex = startIndex,
                    maxResults = maxResults
                )


                if(bookList.items.isNullOrEmpty()){
                    _errorMessageResId.value = R.string.no_books_found_in_api_response
                    _books.value = emptyList()
                } else{
                    _books.value = bookList.items
                    _currentPage.value = page
                }
            } catch (e: Exception){
                Log.e("BooksViewModel", "Error fetching books", e)

                _errorMessageResId.value = R.string.no_books_found_in_api_response
                _books.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun nextPage(){
        if(_books.value.size >= maxResults){
            fetchKotlinBooks(_currentPage.value + 1)
        }
    }

    fun previousPage(){
        if(_currentPage.value > 1){
            fetchKotlinBooks(_currentPage.value - 1)
        }
    }
}