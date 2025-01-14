package com.example.alp_ml.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alp_ml.helper.ONNXModel
import com.example.alp_ml.repositories.QuestionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FormViewModel(context: Context) : ViewModel() {

    private val onnxModel = ONNXModel(context)
    private val medians = floatArrayOf(1.0f,
        1.0f,
        1.0f,
        1.0f,
        29.0f,
        0.0f,
        0.0f,
        0.0f,
        1.0f,
        1.0f,
        1.0f,
        0.0f,
        1.0f,
        0.0f,
        3.0f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        9.0f,
        5.0f,
        7.0f)
    private val iqrs = floatArrayOf(2.0f,
        1.0f,
        1.0f,
        0.0f,
        8.0f,
        1.0f,
        0.0f,
        0.0f,
        0.0f,
        1.0f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        1.0f,
        0.0f,
        2.0f,
        0.0f,
        1.0f,
        4.0f,
        2.0f,
        3.0f)

    private val _predictionResult = MutableStateFlow<FloatArray?>(null)
    val predictionResult: StateFlow<FloatArray?> = _predictionResult

    fun predict(inputs: FloatArray) {
        viewModelScope.launch {
            val scaledInput = robustScale(inputs, medians, iqrs)

            val result = onnxModel.predict(scaledInput)
            Log.d("Prediction Result in VM","Prediction Result ${result.contentToString()}")
            _predictionResult.value = result
        }
    }

    fun convert(answers: MutableList<String?>): FloatArray {
        val orderedData = mutableListOf<Float>()

        if (answers.isNotEmpty()) {
            with(QuestionRepository.questions) {
                forEach { question ->
                    when (question.id) {
                        1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 17 ->
                            orderedData.add(when (answers[question.id-1]) {"false" ->0f; else -> 1f})

                        4, 15, 16, 22 ->
                            orderedData.add((answers[question.id-1] as String).toFloat())

                        14 ->
                            orderedData.add(healthToScale(answers[question.id-1] as String))

                        18 ->
                            orderedData.add(
                                when (answers[question.id-1]) {
                                    "Male" -> 1f; "Female" -> 0f; else -> 0f
                                }
                            )

                        19 ->
                            orderedData.add(ageToScale(Integer.parseInt(answers[question.id-1] as String)))

                        20 ->
                            orderedData.add(educationToScale(answers[question.id-1] as String))

                        21 ->
                            orderedData.add(incomeToScale(Integer.parseInt(answers[question.id-1] as String)))

                    }
                }
            }
        }
        Log.d("BMI", "Weight ${orderedData[3]}")
        Log.d("BMI", "Height ${orderedData[21]}")
        orderedData[3] = bmiCalculator(orderedData[21],orderedData[3])

        orderedData.removeAt(orderedData.lastIndex)

        return orderedData.toFloatArray()
    }


    private fun ageToScale(age: Int): Float {
        return when {
            age in 18..24 -> 1f
            age in 25..29 -> 2f
            age in 30..34 -> 3f
            age in 35..39 -> 4f
            age in 40..44 -> 5f
            age in 45..49 -> 6f
            age in 50..54 -> 7f
            age in 55..59 -> 8f
            age in 60..64 -> 9f
            age in 65..69 -> 10f
            age in 70..74 -> 11f
            age in 75..79 -> 12f
            age >= 80 -> 13f
            else -> 0f // Default value
        }
    }

    private fun educationToScale(education: String): Float {
        return when (education) {
            "Never attended school" -> 1f
            "Elementary school" -> 2f
            "Middle school" -> 3f
            "High school" -> 4f
            "Some college" -> 5f
            "Graduate" -> 6f
            else -> 0f // Default value
        }
    }

    private fun healthToScale(health: String): Float {
        return when (health) {
            "Excellent" -> 1f
            "Very Good" -> 2f
            "Good" -> 3f
            "Fair" -> 4f
            "Poor" -> 5f
            else -> 0f
        }
    }

    private fun incomeToScale(income: Int): Float {
        return when (income) {
            in 0..9999 -> 1f
            in 10000..14999 -> 2f
            in 15000..19999 -> 3f
            in 20000..24999 -> 4f
            in 25000..34999 -> 5f
            in 35000..49999 -> 6f
            in 50000..74999 -> 7f
            75000 -> 8f
            else -> 0f // Default value
        }
    }

    private fun robustScale(input: FloatArray, medians: FloatArray, iqrs: FloatArray): FloatArray {
        return FloatArray(input.size) { i ->
            if (iqrs[i] != 0f) {
                (input[i] - medians[i]) / iqrs[i]
            } else {
                0f // Handle cases where IQR is 0 to avoid division by zero
            }
        }
    }

    private fun bmiCalculator(height: Float, weight: Float): Float{
        return weight / ((height/100) * (height/100))
    }
}
