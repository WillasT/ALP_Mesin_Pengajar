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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.alp_ml.ui.theme.background_col
import com.example.alp_ml.ui.theme.button_col
import com.example.alp_ml.ui.theme.buttonchoose_col
import com.example.alp_ml.ui.theme.heading_col
import com.example.alp_ml.ui.theme.textfield_col
import com.example.alp_ml.ui.theme.textfieldborder_col
import com.example.alp_ml.ui.theme.tiro_telugu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = buttonchoose_col)
    ) {
        Text(
            text = "Diabetes Test",
            fontSize = 64.sp,
            fontFamily = tiro_telugu,
            color = Color.White,
            modifier = Modifier.padding(48.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen()
}