package com.example.movieapp.repository

import android.content.Context
import com.example.movieapp.model.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class MovieRepository (private val context: Context) {
    fun getMoviesFromJson(context: Context): List<Movie> {
        val jsonString: String
        try {
            //Open the asset file and read it into a String
            jsonString = context.assets.open("movies.json").bufferedReader().use { it.readText() }

            // Define the type of data we want (A List of Movies)
            val listMovieType = object : TypeToken<List<Movie>>() {}.type

            // Use Gson to parse the string into the List
            return Gson().fromJson(jsonString, listMovieType)

        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }
    }
}