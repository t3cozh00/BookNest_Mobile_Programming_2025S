# 📚 BookNest - Kotlin Android App

BookNest is a mobile application built with **Kotlin** and **Jetpack Compose** that allows users to explore books using the **Google Books API**. The app provides details such as book title, author, description, and cover images.

---

## 🚀 Features

- 📖 **View a list of Kotlin books** fetched from the Google Books API.
- 🔍 **Click a book to see details** including title, authors, publisher, and description.
- 📷 **Displays book cover images** using Coil image loading.
- 🛒 **Navigate to the Google Play Books store** to purchase the book.
- ⏩ **Paginated book list** for easy browsing.
- 🎨 **Modern UI** using Jetpack Compose.

---

## 📦 Tech Stack

- **Programming Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Navigation:** Jetpack Navigation
- **Data Fetching:** Retrofit
- **State Management:** ViewModel + StateFlow
- **Image Loading:** Coil
- **Backend API:** Google Books API

---

## 🔧 Installation & Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/t3cozh00/BookNest_Mobile_Programming_2025S

2. **Open the project in Android Studio.**

3. **Add your Google Books API Key.**

4. **Sync Gradle and Run the App! 🚀**

---

## ⚠️ Important Note

1. **Google Books API sometimes provides HTTP links instead of HTTPS, which Coil does not load by default**

   ```sh
   To fix this issue, ensure that the image URL is converted from ↳"http://" to "https://".

2. **Save Strings under resource file**

   - **Better localization support** → Easily translate text to different languages.
   - **Improved maintainability** → Centralizes all text, making future updates easier.
   - **Follows best practices** → Hardcoded strings should be avoided in the code.

3. **Secure API Key**

   - Store api key in local.properties(.gitignore)
   - Access api key (build.gradle.kts)
   - BuildConfig.xxx

---

## 🛠️ Project Structure

      📦 BookNest
      ┣ 📂 app/src/main/java/com/example/booknestapp
      ┃ ┣ 📂 models        # Data models (Book)
      ┃ ┣ 📂 api           # Retrofit API interface
      ┃ ┣ 📂 viewmodels    # ViewModel for data management
      ┃ ┣ 📂 ui
      ┃ ┃ ┣ 📂 screens     # Composable screens
      ┃ ┃ ┣ 📂 components  # UI components (BookItemView)
      ┃ ┃ ┣ 📂 topbar      # App Navigation setup
      ┃ ┃ ┗ 📂 theme       # App theme and styling
      ┃ ┣ 🗒️ MainActivity
      ┗ 📜 README.md


---

## 🕊️ Feature Improvements

- Search functionality to find any book.
- Favorites feature to save books for later.
- Review and rate books.
- Comment other users reviews.
- Dark Mode for better accessibility.
- AI-powered recommendations to suggest books users might be interested in.

