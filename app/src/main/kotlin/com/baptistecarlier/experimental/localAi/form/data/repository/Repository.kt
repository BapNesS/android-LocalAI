package com.baptistecarlier.experimental.localAi.form.data.repository

import com.baptistecarlier.experimental.localAi.form.data.model.FormAnswer

/**
 * Repository interface for fetching form answers.
 */
internal fun interface Repository {
    /**
     * Fetches a form answer based on the provided prompt.
     *
     * @param prompt The prompt to generate the form answer for.
     * @return A [Result] containing the [FormAnswer] or an error.
     */
    suspend fun getFormAnswer(prompt: String): Result<FormAnswer>
}

