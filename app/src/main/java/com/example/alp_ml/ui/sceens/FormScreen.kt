package com.example.alp_ml.ui.sceens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_ml.model.Question
import com.example.alp_ml.model.Type
import com.example.alp_ml.ui.components.BooleanInput
import com.example.alp_ml.ui.components.DecimalInput
import com.example.alp_ml.ui.components.NavigationButton
import com.example.alp_ml.ui.components.ScaleInput
import com.example.alp_ml.ui.theme.background_col
import com.example.alp_ml.ui.theme.heading_col
import com.example.alp_ml.ui.theme.tiro_telugu
import com.example.alp_ml.utils.ValidationUtils
import com.example.alp_ml.viewmodel.FormViewModel

@Composable
fun FormScreen(
    questions: List<Question>,
    viewModel: FormViewModel,
    onSubmit: (List<Question>, FloatArray?) -> Unit
) {
    // State to hold the current step (page) in the form
    var currentStep by remember { mutableIntStateOf(0) }

    // State to hold the user's answers
    val answers = remember { mutableStateListOf<String?>() }

    // State to hold validation error messages
    val validationError = remember { mutableStateOf<String?>(null) }

    // State for loading and submission
    var isLoading by remember { mutableStateOf(false) }

    // Initialize answers list with null values if empty
    if (answers.isEmpty()) {
        questions.forEach { _ -> answers.add(null) }
    }

    // Get the current question for this step
    val currentQuestion = questions.getOrNull(currentStep)

    // Observe prediction result
    val predictionResult by viewModel.predictionResult.collectAsState()

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
                        text = if (isLoading) "Loading..." else "Submit",
                        modifier = Modifier.weight(1f),
                        onClick = {
                            if (validateAnswer(
                                    currentQuestion,
                                    answers[currentStep],
                                    validationError
                                )
                            ) {
                                // Convert answers to FloatArray for prediction
                                val inputData = viewModel.convert(answers)
                                print("input data converted: $inputData")

                                // Trigger prediction
                                isLoading = true
                                viewModel.predict(inputData)
                            }
                        },
                        enabled = !isLoading // Disable the button if loading
                    )
                } else {
                    NavigationButton(
                        text = "Next",
                        modifier = Modifier.weight(1f),
                        onClick = {
                            if (validateAnswer(
                                    currentQuestion,
                                    answers[currentStep],
                                    validationError
                                )
                            ) {
                                currentStep++
                            }
                        }
                    )
                }
            }

            // Auto-submit once prediction is done
            if (predictionResult != null && isLoading) {
                isLoading = false
                onSubmit(questions, predictionResult)
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
            if (!ValidationUtils.validateBooleanInput(
                    answer ?: "",
                    question.options ?: emptyList()
                )
            ) {
                validationError.value = "Please select a valid option."
                false
            } else {
                validationError.value = null
                true
            }
        }

        Type.SCALE -> {
            if (!ValidationUtils.validateScaleInput(
                    answer ?: "",
                    question.options ?: emptyList()
                )
            ) {
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

//@Preview
//@Composable
//fun FormScreenPreview() {
//
//    val context = LocalContext.current
//    val questions = QuestionRepository.questions
//
//    FormScreenWithViewModel(
//        context = context,
//        questions = questions
//    ) { answers, predictionResult ->
//        println("Submitted answers: $answers")
//        println("Prediction result: ${predictionResult?.joinToString()}")
//    }
//}
