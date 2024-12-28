package com.example.alp_ml.ui.sceens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_ml.ui.components.*
import com.example.alp_ml.model.Question
import com.example.alp_ml.model.Type
import com.example.alp_ml.repositories.QuestionRepository
import com.example.alp_ml.ui.theme.background_col
import com.example.alp_ml.ui.theme.heading_col
import com.example.alp_ml.ui.theme.tiro_telugu
import com.example.alp_ml.utils.ValidationUtils

@Composable
fun FormScreen(
    questions: List<Question>,
    onSubmit: (List<Question>) -> Unit
) {
    // State to hold the current step (page) in the form
    var currentStep by remember { mutableIntStateOf(0) }

    // State to hold the user's answers
    val answers = remember { mutableStateListOf<String?>() }

    // State to hold validation error messages
    val validationError = remember { mutableStateOf<String?>(null) }

    // Initialize answers list with null values if empty
    if (answers.isEmpty()) {
        questions.forEach { _ -> answers.add(null) }
    }

    // Get the current question for this step
    val currentQuestion = questions.getOrNull(currentStep)

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = background_col)
                .padding(48.dp)
        ) {
            // Top content (Question and Input Fields)
            currentQuestion?.let { question ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f), // Allocate remaining space to this section
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = question.questionText,
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = tiro_telugu,
                            color = heading_col,
                            lineHeight = 48.sp
                        )
                        Text(
                            text = question.questionDetail,
                            fontSize = 15.sp,
                            color = heading_col,
                            fontFamily = tiro_telugu,
                            lineHeight = 16.sp
                        )

                        // Display validation error if present
                        validationError.value?.let { error ->
                            Text(
                                text = error,
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }

                    // Render input field based on question type
                    when (question.type) {
                        Type.NUMBER -> {
                            DecimalInput(
                                value = answers[currentStep] ?: "",
                                onValueChange = { answers[currentStep] = it },
                                label = question.questionText,
                            )
                        }

                        Type.BOOLEAN -> {
                            BooleanInput(
                                selectedOption = answers[currentStep]?.toBoolean() ?: false,
                                onOptionSelected = { answers[currentStep] = it.toString() },
                                yesLabel = "Yes",
                                noLabel = "No"
                            )
                        }

                        Type.SCALE -> {
                            question.options?.let {
                                ScaleInput(
                                    options = it,
                                    selectedOption = answers[currentStep],
                                    onOptionSelected = { answers[currentStep] = it }
                                )
                            }
                        }
                    }
                }
            }

            // Navigation buttons (fixed at the bottom)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Show "Previous" button if not on the first step
                if (currentStep > 0) {
                    NavigationButton(
                        text = "Previous",
                        modifier = Modifier.weight(1f),
                        onClick = { currentStep-- }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }

                // Show "Next" or "Submit" button
                if (currentStep == questions.size - 1) {
                    NavigationButton(
                        text = "Submit",
                        modifier = Modifier.weight(1f),
                        onClick = {
                            if (validateAnswer(currentQuestion, answers[currentStep], validationError)) {
                                onSubmit(questions.mapIndexed { _, question ->
                                    question.copy(
                                        id = question.id,
                                        questionText = question.questionText,
                                        questionDetail = question.questionDetail,
                                        type = question.type,
                                        options = question.options,
                                    )
                                })
                            }
                        }
                    )
                } else {
                    NavigationButton(
                        text = "Next",
                        modifier = Modifier.weight(1f),
                        onClick = {
                            if (validateAnswer(currentQuestion, answers[currentStep], validationError)) {
                                currentStep++
                            }
                        }
                    )
                }
            }
        }
    }
}


private fun validateAnswer(
    question: Question?,
    answer: String?,
    validationError: MutableState<String?>
): Boolean {
    return when (question?.type) {
        Type.NUMBER -> {
            if (!ValidationUtils.validateNumberInput(answer ?: "")) {
                validationError.value = "Please enter a valid number."
                false
            } else {
                validationError.value = null
                true
            }
        }
        Type.BOOLEAN -> {
            if (!ValidationUtils.validateBooleanInput(answer ?: "", question.options ?: emptyList())) {
                validationError.value = "Please select a valid option."
                false
            } else {
                validationError.value = null
                true
            }
        }
        Type.SCALE -> {
            if (!ValidationUtils.validateScaleInput(answer ?: "", question.options ?: emptyList())) {
                validationError.value = "Please select a valid option."
                false
            } else {
                validationError.value = null
                true
            }
        }
        else -> {
            validationError.value = null
            true
        }
    }
}




@Preview
@Composable
fun FormScreenPreview() {
    FormScreen(questions = QuestionRepository.questions) { answers ->
        println("Submitted answers: $answers") }
}
