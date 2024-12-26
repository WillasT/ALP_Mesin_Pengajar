package com.example.alp_ml

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.alp_ml.ui.theme.background_col
import com.example.alp_ml.ui.theme.button_col
import com.example.alp_ml.ui.theme.buttonchoose_col
import com.example.alp_ml.ui.theme.buttonchoosed_col
import com.example.alp_ml.ui.theme.heading_col
import com.example.alp_ml.ui.theme.tiro_telugu

@Composable
fun HealthInputScreen() {
    MaterialTheme {
        var selectedOption by remember { mutableStateOf<String?>(null) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = background_col)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(48.dp)
            ) {
                Column(modifier = Modifier.align(Alignment.TopStart)) {
                    Text(
                        text = "What would you say your general health condition is ?",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = tiro_telugu,
                        color = heading_col,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 32.dp),
                        lineHeight = 48.sp
                    )

                    Text(
                        text = "Pick one of the options below",
                        fontSize = 15.sp,
                        color = heading_col,
                        fontFamily = tiro_telugu,
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .fillMaxWidth(),
                        lineHeight = 16.sp
                    )
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { selectedOption = "Excellent" },
                            modifier = Modifier
                                .height(48.dp)
                                .weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedOption == "Excellent")
                                    buttonchoosed_col else buttonchoose_col,
                            ),
                            shape = RoundedCornerShape(16.dp) // Set the desired corner radius
                        ) {
                            Text(
                                text = "Excellent",
                                fontFamily = tiro_telugu,
                                fontSize = 16.sp,
                            )
                        }
                        Spacer(modifier = Modifier.padding(6.dp))
                        Button(
                            onClick = { selectedOption = "Very Good" },
                            modifier = Modifier
                                .height(48.dp)
                                .weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedOption == "Very Good")
                                    buttonchoosed_col else buttonchoose_col,
                            ),
                            shape = RoundedCornerShape(16.dp) // Set the desired corner radius
                        ) {
                            Text(
                                text = "Very Good",
                                fontFamily = tiro_telugu,
                                fontSize = 16.sp,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { selectedOption = "Good" },
                            modifier = Modifier
                                .height(48.dp)
                                .weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedOption == "Good")
                                    buttonchoosed_col else buttonchoose_col,
                            ),
                            shape = RoundedCornerShape(16.dp) // Set the desired corner radius
                        ) {
                            Text(
                                text = "Good",
                                fontFamily = tiro_telugu,
                                fontSize = 16.sp,
                            )
                        }
                        Spacer(modifier = Modifier.padding(6.dp))
                        Button(
                            onClick = { selectedOption = "Fair" },
                            modifier = Modifier
                                .height(48.dp)
                                .weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedOption == "Fair")
                                    buttonchoosed_col else buttonchoose_col,
                            ),
                            shape = RoundedCornerShape(16.dp) // Set the desired corner radius
                        ) {
                            Text(
                                text = "Fair",
                                fontFamily = tiro_telugu,
                                fontSize = 16.sp,
                            )
                        }
                        Spacer(modifier = Modifier.padding(6.dp))
                        Button(
                            onClick = { selectedOption = "Poor" },
                            modifier = Modifier
                                .height(48.dp)
                                .weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedOption == "Poor")
                                    buttonchoosed_col else buttonchoose_col,
                            ),
                            shape = RoundedCornerShape(16.dp) // Set the desired corner radius
                        ) {
                            Text(
                                text = "Poor",
                                fontFamily = tiro_telugu,
                                fontSize = 16.sp,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(vertical = 12.dp))

                    Button(
                        onClick = { /* Handle next action */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = button_col,
                        ),
                        shape = RoundedCornerShape(16.dp) // Set the desired corner radius
                    ) {
                        Text(
                            text = "Next",
                            fontFamily = tiro_telugu,
                            fontSize = 16.sp
                        )
                    }

                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HealthInputScreenPreview() {
    HealthInputScreen()
}