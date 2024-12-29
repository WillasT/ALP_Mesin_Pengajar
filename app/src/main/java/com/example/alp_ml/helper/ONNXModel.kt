package com.example.alp_ml.helper

import ai.onnxruntime.*
import android.content.Context
import android.util.Log
import java.nio.FloatBuffer

object ONNXEnvManager {
    val env: OrtEnvironment by lazy {
        OrtEnvironment.getEnvironment()
    }
}

class ONNXModel(context: Context) {

    private var session: OrtSession? = null

    init {
        try {
            // Use the shared environment
            val env = ONNXEnvManager.env
            Log.d("ONNXModel", "OrtEnvironment initialized: ${env != null}")

            // Load the ONNX model
            val assetManager = context.assets
            val modelInputStream = assetManager.open("random_forest_model.onnx")
            val modelBytes = modelInputStream.readBytes()
            Log.d("ONNXModel", "Model loaded successfully, size: ${modelBytes.size}")

            // Initialize session
            session = env.createSession(modelBytes)
            Log.d("ONNXModel", "Session initialized: ${session != null}")

        } catch (e: OrtException) {
            Log.e("ONNXModel", "OrtException during initialization: ${e.message}")
        } catch (e: Exception) {
            Log.e("ONNXModel", "General Exception during initialization: ${e.message}")
        }
    }

    fun predict(inputData: FloatArray): FloatArray? {
        var inputTensor: OnnxTensor? = null
        try {
            // Debug logging
            Log.d("ONNXModel", "Input Data: ${inputData.contentToString()}")

            val session = this.session ?: run {
                Log.e("ONNXModel", "Session is null")
                return null
            }

            // Log model info
            Log.d("ONNXModel", "Input Names: ${session.inputNames}")
            Log.d("ONNXModel", "Output Names: ${session.outputNames}")

            val inputShape = longArrayOf(1, inputData.size.toLong())
            inputTensor = OnnxTensor.createTensor(
                OrtEnvironment.getEnvironment(),
                FloatBuffer.wrap(inputData),
                inputShape
            )

            // Log input tensor info
            Log.d("ONNXModel", "Input tensor created successfully")


            val results = session.run(mapOf("input" to inputTensor))

            // Get probability output specifically
            val probabilityTensor = results.get(0) as OnnxTensor
            Log.d("ONNXModel", "ONNXTensor info: ${probabilityTensor.info}")
            Log.d("ONNXModel", "ONNXTensor value: ${probabilityTensor.type}")

            val floatBuffer = probabilityTensor.longBuffer
            if (floatBuffer == null) {
                Log.e("ONNXModel", "Float buffer is null")
                return null
            }

            val size = floatBuffer.remaining()
            val output = FloatArray(size) { floatBuffer.get().toFloat() }

            Log.d("ONNXModel", "Probabilities: ${output.contentToString()}")

            probabilityTensor.close()
            return output

        } catch (e: Exception) {
            Log.e("ONNXModel", "Prediction error", e)
            e.printStackTrace()
            return null
        } finally {
            inputTensor?.close()
        }
    }
}
