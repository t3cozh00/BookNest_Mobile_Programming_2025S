package com.example.booknestapp.api

import com.example.booknestapp.models.BookResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://www.googleapis.com/books/v1/"

interface BooksApi {
    @GET("volumes")
    suspend fun getKotlinBooks(
        @Query("q") query: String = "kotlin",
        @Query("printType") printType: String = "books",
        @Query("key") apiKey: String,
        @Query("startIndex") startIndex: Int,
        @Query("maxResults") maxResults: Int

    ) : BookResponse

    companion object {
        private var booksService: BooksApi? = null

        fun getInstance(): BooksApi {
            if (booksService === null){
                booksService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(BooksApi::class.java)
            }
            return booksService!!
        }
    }
}