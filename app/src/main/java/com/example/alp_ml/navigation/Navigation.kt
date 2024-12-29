package com.example.alp_ml.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.alp_ml.repositories.QuestionRepository
import com.example.alp_ml.ui.sceens.FormScreen
import com.example.alp_ml.ui.sceens.HomeScreen
import com.example.alp_ml.ui.sceens.ResultScreen
import com.example.alp_ml.ui.sceens.SplashScreen
import com.example.alp_ml.viewmodel.FormViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        // Splash Screen Route
        composable("splash") { SplashScreen { navController.navigate("home") } }

        // Home Screen Route
        composable("home") { HomeScreen(navController) } // Pass NavController here

        // Form Screen Route
        composable("form") {
            val context = LocalContext.current
            FormScreen(
                questions = QuestionRepository.questions,
                onSubmit = { answers, predictionResult ->
                    println("Submitted answers: $answers")
                    Log.d("Prediction Result", "Prediction result in Navigation ${predictionResult.contentToString()}")

                    // Convert the FloatArray to a comma-separated string
                    val resultString = predictionResult?.joinToString(",") ?: ""

                    // Pass the resultString as an argument to the "result" screen
                    navController.navigate("result/$resultString")
                },
                viewModel = FormViewModel(context = context)
            )
        }

        // Result Screen Route
        composable("result/{resultString}") { backStackEntry ->
            val resultString = backStackEntry.arguments?.getString("resultString") ?: ""

            // Convert the resultString back into a FloatArray
            val predictionResult = resultString.split(",").mapNotNull { it.toFloatOrNull() }.toFloatArray()

            ResultScreen(navController, predictionResult)
        }
    }
}
