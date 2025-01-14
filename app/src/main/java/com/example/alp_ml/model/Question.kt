package com.example.alp_ml.model

import com.example.alp_ml.model.Type

data class Question(
    val id: Int,
    val questionText: String,
    val questionDetail: String,
    val type:Type,
    val options: List<String>? = null
)