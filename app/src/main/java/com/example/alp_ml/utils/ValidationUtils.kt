package com.example.alp_ml.utils

object ValidationUtils {
    fun validateNumberInput(input: String): Boolean {
        return input.toDoubleOrNull() != null
    }

    fun validateBooleanInput(input: String, options: List<String>): Boolean {
        return input.equals("true", ignoreCase = true) ||
                input.equals("false", ignoreCase = true) ||
                options.contains(input)
    }

    fun validateScaleInput(input: String, options: List<String>): Boolean {
        return options.contains(input)
    }
}
