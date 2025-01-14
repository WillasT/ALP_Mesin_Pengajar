package com.example.alp_ml

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.alp_ml.navigation.AppNavigation
import com.example.alp_ml.ui.theme.ALP_MLTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ALP_MLTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}