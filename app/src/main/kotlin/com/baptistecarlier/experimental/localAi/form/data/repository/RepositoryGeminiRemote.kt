package com.baptistecarlier.experimental.localAi.form.data.repository

import com.baptistecarlier.experimental.localAi.BuildConfig
import com.baptistecarlier.experimental.localAi.form.data.model.FormAnswer
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.content

internal class RepositoryGeminiRemote(
    private val generativeModel : GenerativeModel,
) : Repository {

    override suspend fun getFormAnswer(
        prompt: String
    ): Result<FormAnswer> = runCatching {
        check(BuildConfig.GEMINI_API_KEY.isNotEmpty()) {
            "GEMINI_API_KEY must be set in BuildConfig to use Gemini Remote"
        }

        val response: GenerateContentResponse = generativeModel.generateContent(
            content {
                text(prompt)
            }
        )

        val generatedText = response.text
        return if (!generatedText.isNullOrBlank()) {
            Result.success(FormAnswer(generatedText))
        } else {
            Result.failure(IllegalStateException("Empty response from Gemini model"))
        }

    }

}

