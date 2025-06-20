package com.baptistecarlier.experimental.localAi.form.data.di

import com.baptistecarlier.experimental.localAi.BuildConfig
import com.baptistecarlier.experimental.localAi.form.data.repository.RepositoryFactory
import com.baptistecarlier.experimental.localAi.form.data.repository.RepositoryGeminiLocal
import com.baptistecarlier.experimental.localAi.form.data.repository.RepositoryGeminiRemote
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.google.ai.client.generativeai.GenerativeModel as RemoteGenerativeModel
import com.google.ai.client.generativeai.type.generationConfig as remoteGenerationConfig
import com.google.ai.edge.aicore.GenerativeModel as LocalGenerativeModel
import com.google.ai.edge.aicore.generationConfig as localGenerationConfig

private const val Temperature = 0.2f
private const val TopK = 16

/**
 * Koin module for providing data layer dependencies.
 */
internal val dataModule = module {
    // Local
    single {
        LocalGenerativeModel(
            generationConfig = localGenerationConfig {
                context = androidApplication()
                temperature = Temperature
                topK = TopK
            },
        )
    }
    factoryOf(::RepositoryGeminiLocal)
    // Remote
    single {
        RemoteGenerativeModel(
            modelName = "gemini-1.5-flash",
            // TODO Don't forget it
            apiKey = BuildConfig.GEMINI_API_KEY,
            generationConfig = remoteGenerationConfig {
                temperature = Temperature
                topK = TopK
            },
        )
    }
    factoryOf(::RepositoryGeminiRemote)

    // And the beautiful factory
    factoryOf(::RepositoryFactory)
}
