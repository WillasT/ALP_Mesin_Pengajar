package com.example.alp_ml

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
import com.example.alp_ml.ui.theme.background_col
import com.example.alp_ml.ui.theme.buttonchoose_col
import com.example.alp_ml.ui.theme.continue_col
import com.example.alp_ml.ui.theme.heading_col
import com.example.alp_ml.ui.theme.tiro_telugu

@Composable
fun ResultScreen() {
    MaterialTheme {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = background_col)
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
                    color = heading_col,
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
                    colors = CardDefaults.cardColors(containerColor = buttonchoose_col)
                ) {
                    Column {
                        Text(
                            text = "You have 10% chance to get diabetes",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = tiro_telugu,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 64.dp, top = 48.dp),
                            lineHeight = 48.sp
                        )
                        Text(
                            text = "You may want to seek medical treatments from professionals",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = tiro_telugu,
                            color = Color.White,
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
                            onClick = { /* Handle next action */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(64.dp)
                                .padding(horizontal = 64.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = continue_col,
                            ),
                            shape = RoundedCornerShape(16.dp) // Set the desired corner radius
                        ) {
                            Text(
                                text = "Continue",
                                fontFamily = tiro_telugu,
                                fontSize = 20.sp,
                                color = buttonchoose_col
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
    ResultScreen()
}