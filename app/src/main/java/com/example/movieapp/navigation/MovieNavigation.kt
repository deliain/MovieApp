package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.home.HomeScreen
import com.example.movieapp.screens.details.DetailsScreen
import com.example.movieapp.viewmodel.MovieViewModel

@Composable
fun MovieNavigation () {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name){
        composable(MovieScreens.HomeScreen.name){
            val homeViewModel = viewModel<MovieViewModel>()
            HomeScreen(
                navController = navController,
                viewModel = homeViewModel
            )
        }
        composable(MovieScreens.DetailScreen.name+"/{movie}", arguments = listOf(navArgument(name = "movie") {type =
            NavType.StringType})){
            backStackEntry ->
            val detailViewModel = viewModel<MovieViewModel>()
            DetailsScreen(navController = navController, movieId = backStackEntry.arguments?.getString("movie"), viewModel = detailViewModel)
        }
    }
}