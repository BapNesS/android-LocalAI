package com.baptistecarlier.experimental.localAi

import android.app.Application
import com.baptistecarlier.experimental.localAi.form.data.di.dataModule
import com.baptistecarlier.experimental.localAi.form.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

internal class LocalAiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Init Koin
        GlobalContext.startKoin {
            androidContext(this@LocalAiApplication)
            androidLogger()
            modules(
                uiModule,
                dataModule,
            )
        }
    }
}