package com.example.alp_ml.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_ml.ui.theme.button_col
import com.example.alp_ml.ui.theme.tiro_telugu


@Composable
fun NavigationButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            // Use a stronger, more contrasting color when enabled
            containerColor = if (enabled) MaterialTheme.colorScheme.primary.copy(alpha = 0.9f) else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
        ),
        shape = RoundedCornerShape(16.dp),
        enabled = enabled
    ) {
        Text(
            text = text,
            fontFamily = tiro_telugu,
            fontSize = 16.sp,
            // Make text color more prominent against the background
            color = if (enabled) Color.White else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )
    }
}

