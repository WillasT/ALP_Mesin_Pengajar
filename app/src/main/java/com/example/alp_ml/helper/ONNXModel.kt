package com.example.alp_ml.helper

import ai.onnxruntime.*
import android.content.Context
import java.nio.FloatBuffer

class ONNXModel(context: Context) {

    private var session: OrtSession? = null

    init {
        try {
            // Initialize ONNX Runtime Environment
            val env = OrtEnvironment.getEnvironment()

            // Load the ONNX model from the assets folder
            val assetManager = context.assets
            val modelInputStream = assetManager.open("random_forest_model.onnx")
            val modelBytes = modelInputStream.readBytes()
            session = env.createSession(modelBytes)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun predict(inputData: FloatArray): FloatArray? {
        try {
            // Prepare input tensor
            val inputShape = longArrayOf(1, inputData.size.toLong()) // Adjust to model's input size
            val inputTensor = OnnxTensor.createTensor(
                OrtEnvironment.getEnvironment(),
                FloatBuffer.wrap(inputData),
                inputShape
            )

            // Run inference
            val results = session?.run(mapOf("input" to inputTensor))

            // Extract output tensor (assuming single output)
            val output = results?.firstOrNull()?.value as? Array<FloatArray>
            return output?.firstOrNull()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}
