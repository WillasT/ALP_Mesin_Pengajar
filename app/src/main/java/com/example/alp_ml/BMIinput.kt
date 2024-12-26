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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.alp_ml.ui.theme.background_col
import com.example.alp_ml.ui.theme.button_col
import com.example.alp_ml.ui.theme.heading_col
import com.example.alp_ml.ui.theme.textfield_col
import com.example.alp_ml.ui.theme.textfieldborder_col
import com.example.alp_ml.ui.theme.tiro_telugu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmiInputScreen() {
    MaterialTheme {
        var bmiInput by remember { mutableStateOf("") }
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
                        text = "What is your BMI?",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = tiro_telugu,
                        color = heading_col,
                        modifier = Modifier.fillMaxWidth(),
                        lineHeight = 48.sp
                    )

                    Text(
                        text = "Input must be a type of number in Decimal",
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
                    OutlinedTextField(
                        value = bmiInput,
                        onValueChange = { bmiInput = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = textfieldborder_col,
                            unfocusedBorderColor = textfieldborder_col,
                            containerColor = textfield_col
                        )
                    )

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
fun BmiInputScreenPreview() {
    BmiInputScreen()
}