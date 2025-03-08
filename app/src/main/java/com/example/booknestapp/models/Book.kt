package com.example.booknestapp.models

data class BookResponse(
    val items: List<BookItem>?
)

data class BookItem(
    val id: String,
    val selfLink: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo (
    val title: String,
    val subtitle: String? = null,
    val authors: List<String>? = null,
    val publisher: String? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val pageCount: Int? = null,
    val categories: List<String>? = null,
    val imageLinks: ImageLinks? = null
)

data class ImageLinks(
    val smallThumbnail: String?,
    val thumbnail: String?
)


