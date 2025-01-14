package com.example.alp_ml.ui.sceens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.alp_ml.ui.theme.background_col
import com.example.alp_ml.ui.theme.buttonchoose_col
import com.example.alp_ml.ui.theme.continue_col
import com.example.alp_ml.ui.theme.heading_col
import com.example.alp_ml.ui.theme.tiro_telugu

@SuppressLint("DefaultLocale")
@Composable
fun ResultScreen(navController: NavController, result: FloatArray) {
    MaterialTheme {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background) // Use theme's background color
        ) {

            Column(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 36.dp, start = 48.dp)
            ) {
                Text(
                    text = "Your \nResults",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = tiro_telugu,
                    color = MaterialTheme.colorScheme.onBackground, // Adjusted to use theme's onBackground color
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 32.dp),
                    lineHeight = 64.sp
                )
            }

            Column(
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Card(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp))
                        .fillMaxSize(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary) // Adjusted to use theme's secondary color
                ) {
                    Column {
                        Text(
                            text = "${String.format("%.2f", result[1] * 100)}% chance of Diabetes",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = tiro_telugu,
                            color = MaterialTheme.colorScheme.onSecondary, // Adjusted to use theme's onSecondary color
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 64.dp, top = 48.dp),
                            lineHeight = 48.sp
                        )
                        Text(
                            text = when (result[0]) {
                                2.0f -> "You have\nhigh chance\nof diabetes"
                                else -> "You have\nlow chance\nof diabetes"
                            },
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = tiro_telugu,
                            color = MaterialTheme.colorScheme.onSecondary, // Adjusted to use theme's onSecondary color
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 64.dp, top = 48.dp),
                            lineHeight = 48.sp
                        )
                        Text(
                            text = when (result[0]) {
                                2.0f -> "You may want to seek medical treatments from professionals"
                                else -> "Keep yourself healthy"
                            },
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = tiro_telugu,
                            color = MaterialTheme.colorScheme.onSecondary, // Adjusted to use theme's onSecondary color
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 64.dp, end = 96.dp),
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier
                            .padding(bottom = 96.dp)
                            .fillMaxSize()
                    ) {
                        Button(
                            onClick = { navController.navigate("home") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(64.dp)
                                .padding(horizontal = 64.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f) , // Adjusted to use theme's primary color
                            ),
                            shape = RoundedCornerShape(16.dp) // Set the desired corner radius
                        ) {
                            Text(
                                text = "Continue",
                                fontFamily = tiro_telugu,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onPrimary // Adjusted to use theme's onPrimary color
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    ResultScreen(
        navController = rememberNavController(),
        result = floatArrayOf(2.0f)
    )
}