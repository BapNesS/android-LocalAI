package com.baptistecarlier.experimental.localAi.form.data.repository

internal class RepositoryFactory(
    private val repositoryGeminiLocal: RepositoryGeminiLocal,
    private val repositoryGeminiRemote: RepositoryGeminiRemote
) {
    // Factory method to get the appropriate repository based on the source
    operator fun invoke(source: Source): Repository {
        return when (source) {
            Source.GeminiLocal -> repositoryGeminiLocal
            Source.GeminiRemote -> repositoryGeminiRemote
        }
    }

}