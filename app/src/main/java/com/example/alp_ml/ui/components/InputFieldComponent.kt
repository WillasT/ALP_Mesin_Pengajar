package com.example.alp_ml.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_ml.ui.theme.buttonchoose_col
import com.example.alp_ml.ui.theme.buttonchoosed_col
import com.example.alp_ml.ui.theme.textfield_col
import com.example.alp_ml.ui.theme.textfieldborder_col
import com.example.alp_ml.ui.theme.tiro_telugu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DecimalInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeholder: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally // Aligning content horizontally center
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.matches(Regex("^\\d*\\.?\\d*$"))) {
                    onValueChange(newValue)
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = textfieldborder_col,
                unfocusedBorderColor = textfieldborder_col,
                containerColor = textfield_col
            ),
            label = label?.let { { Text(it) } },
            placeholder = placeholder?.let { { Text(it) } }
        )
    }
}

@Composable
fun BooleanInput(
    selectedOption: Boolean,
    onOptionSelected: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    yesLabel: String = "Yes",
    noLabel: String = "No"
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally // Aligning content horizontally center
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { onOptionSelected(false) },
                modifier = Modifier
                    .height(48.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!selectedOption)
                        buttonchoosed_col else buttonchoose_col,
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = noLabel,
                    fontFamily = tiro_telugu,
                    fontSize = 16.sp,
                )
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Button(
                onClick = { onOptionSelected(true) },
                modifier = Modifier
                    .height(48.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedOption)
                        buttonchoosed_col else buttonchoose_col,
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = yesLabel,
                    fontFamily = tiro_telugu,
                    fontSize = 16.sp,
                )
            }
        }
    }
}

@Composable
fun ScaleInput(
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        options.chunked(2).forEach { rowOptions ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowOptions.forEach { option ->
                    Button(
                        onClick = { onOptionSelected(option) },
                        modifier = Modifier
                            .height(48.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedOption == option)
                                buttonchoosed_col else buttonchoose_col,
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = option,
                            fontFamily = tiro_telugu,
                            fontSize = 16.sp,
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                }
            }
        }
    }
}
