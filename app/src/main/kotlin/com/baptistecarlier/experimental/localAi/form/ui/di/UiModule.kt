package com.baptistecarlier.experimental.localAi.form.ui.di

import com.baptistecarlier.experimental.localAi.form.ui.viewModel.FormVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module for the UI layer of the form feature.
 *
 * This module provides the ViewModel for the form screen.
 */
internal val uiModule = module {
    viewModel { FormVM(get()) }
}