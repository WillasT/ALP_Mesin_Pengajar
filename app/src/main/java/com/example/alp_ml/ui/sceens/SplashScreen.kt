package com.example.alp_ml.ui.sceens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alp_ml.ui.theme.tiro_telugu

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(3000) // Delay for 3 seconds
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF36804F)),
        contentAlignment = Alignment.TopStart
    ) {
        Text(
            text = """Diabetes
                |Test
            """.trimMargin(),
            color = Color.White,
            style = MaterialTheme.typography.displayLarge,
            fontFamily = tiro_telugu,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(50.dp)
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen { }
}
