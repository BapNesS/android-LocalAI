package com.baptistecarlier.experimental.localAi.form.data.repository

import com.baptistecarlier.experimental.localAi.form.data.model.FormAnswer
import com.google.ai.edge.aicore.GenerativeModel

internal class RepositoryGeminiLocal(
    private val generativeModel: GenerativeModel,
) : Repository {

    override suspend fun getFormAnswer(
        prompt: String
    ): Result<FormAnswer> = runCatching {
        val description = generativeModel.generateContent(prompt).text
            ?: error("No text generated")
        return Result.success(FormAnswer(content = description))
    }

}

