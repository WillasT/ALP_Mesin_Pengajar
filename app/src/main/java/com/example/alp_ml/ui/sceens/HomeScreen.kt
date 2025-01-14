package com.example.alp_ml.ui.sceens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.alp_ml.ui.components.NavigationButton
import com.example.alp_ml.ui.theme.heading_col
import com.example.alp_ml.ui.theme.tiro_telugu
@Composable
fun HomeScreen(navController: NavController) {
    // Pass the NavController as a parameter
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background) // Adjusted to use theme background color
                .padding(48.dp)
        ) {
            Column(modifier = Modifier.align(Alignment.TopStart)) {
                Text(
                    text =
                    """
                        |Diabetes
                        |Test
                    """.trimMargin(),
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = tiro_telugu,
                    color = MaterialTheme.colorScheme.onBackground, // Adjusted to use theme's onBackground color
                    modifier = Modifier.fillMaxWidth(),
                    lineHeight = 48.sp
                )

                Text(
                    text =
                    """
                    Please input actual and accurate
                    information in order to get 
                    the best result
                    """.trimIndent(),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground, // Adjusted to use theme's onBackground color
                    fontFamily = tiro_telugu,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .fillMaxWidth(),
                    lineHeight = 18.sp
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp)
            ) {
                NavigationButton(
                    text = "Start",
                    modifier = Modifier,
                    onClick = { navController.navigate("form") } // Navigate to the form screen
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    // Use a dummy NavController for preview purposes
    HomeScreen(navController = rememberNavController())
}
