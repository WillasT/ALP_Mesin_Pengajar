package com.example.alp_ml.repositories

import com.example.alp_ml.model.Question
import com.example.alp_ml.model.Type

object QuestionRepository {
    val questions = listOf(
        Question(
            id = 1,
            questionText = "Do you have high blood pressure?",
            questionDetail = "",
            type = Type.BOOLEAN
        ),
        Question(
            id = 2,
            questionText = "Do you have high cholesterol?",
            questionDetail = "",
            type = Type.BOOLEAN
        ),
        Question(
            id = 3,
            questionText = "Have you had your cholesterol checked in the past 5 years?",
            questionDetail = "",
            type = Type.BOOLEAN
        ),
        Question(
            id = 4,
            questionText = "What is your Weight",
            questionDetail = "Input your weight in Kilograms (KG)",
            type = Type.NUMBER
        ),
        Question(
            id = 5,
            questionText = "Have you smoked at least 100 cigarettes in your life?",
            questionDetail = "",
            type = Type.BOOLEAN
        ),
        Question(
            id = 6,
            questionText = "Have you ever been told you had a stroke?",
            questionDetail = "",
            type = Type.BOOLEAN
        ),
        Question(
            id = 7,
            questionText = "Have you ever been told you have coronary heart disease?",
            questionDetail = "",
            type = Type.BOOLEAN
        ),
        Question(
            id = 8,
            questionText = "Have you done physical activities in the past 30 days?",
            questionDetail = "",
            type = Type.BOOLEAN
        ),
        Question(
            id = 9,
            questionText = "Do you eat fruits daily?",
            questionDetail = "",
            type = Type.BOOLEAN
        ),
        Question(
            id = 10,
            questionText = "Do you eat vegetables daily?",
            questionDetail = "",
            type = Type.BOOLEAN
        ),
        Question(
            id = 11,
            questionText = "Do you consume alcohol excessively?",
            questionDetail = "No/Yes (men: ≥14 drinks/week, women: ≥7 drinks/week)",
            type = Type.BOOLEAN
        ),
        Question(
            id = 12,
            questionText = "Do you have health coverage?",
            questionDetail = "",
            type = Type.BOOLEAN
        ),
        Question(
            id = 13,
            questionText = "Have you skipped a doctor visit due to cost in the past year?",
            questionDetail = "",
            type = Type.BOOLEAN
        ),
        Question(
            id = 14,
            questionText = "How would you rate your health?",
            questionDetail = "",
            type = Type.SCALE,
            options = listOf("Excellent", "Very Good", "Good", "Fair", "Poor")
        ),
        Question(
            id = 15,
            questionText = "How many days have you experienced poor mental health in the past month?",
            questionDetail = "Provide a number between 1-30.",
            type = Type.NUMBER
        ),
        Question(
            id = 16,
            questionText = "How many days have you experienced physical illness or injury in the past month?",
            questionDetail = "Provide a number between 1-30.",
            type = Type.NUMBER
        ),
        Question(
            id = 17,
            questionText = "Do you have difficulty walking or climbing stairs?",
            questionDetail = "",
            type = Type.BOOLEAN
        ),
        Question(
            id = 18,
            questionText = "What is your sex?",
            questionDetail = "",
            type = Type.SCALE,
            options = listOf("Male", "Female")
        ),
        Question(
            id = 19,
            questionText = "What is your age?",
            questionDetail = "Type numbers only",
            type = Type.NUMBER
        ),
        Question(
            id = 20,
            questionText = "What is your highest education level?",
            questionDetail = "",
            type = Type.SCALE,
            options = listOf(
                "Never attended school",
                "Elementary school",
                "Middle school",
                "High school",
                "Some college",
                "Graduate"
            )
        ),
        Question(
            id = 21,
            questionText = "What is your annual income?",
            questionDetail = "Provide your income in dollars.",
            type = Type.NUMBER
        ),
        Question(
            id = 22,
            questionText = "What is your height?",
            questionDetail = "Input in Centimeters (CM)",
            type = Type.NUMBER
        )
    )
}
