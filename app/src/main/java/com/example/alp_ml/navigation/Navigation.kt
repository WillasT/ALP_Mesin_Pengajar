package com.example.alp_ml.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.alp_ml.repositories.QuestionRepository
import com.example.alp_ml.ui.sceens.FormScreen
import com.example.alp_ml.ui.sceens.HomeScreen
import com.example.alp_ml.ui.sceens.SplashScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        // Splash Screen Route
        composable("splash") { SplashScreen { navController.navigate("home") } }

        // Home Screen Route
        composable("home") { HomeScreen() }
        composable("form") { FormScreen(questions = QuestionRepository.questions, onSubmit = {}) }
    }
}