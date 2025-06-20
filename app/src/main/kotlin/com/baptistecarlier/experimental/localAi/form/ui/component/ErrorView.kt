package com.baptistecarlier.experimental.localAi.form.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.baptistecarlier.experimental.localAi.BuildConfig
import com.baptistecarlier.experimental.localAi.common.theme.LocalAiTheme
import com.baptistecarlier.experimental.localAi.form.ui.viewModel.State

@Composable
internal fun ErrorView(state: State.Error) {
    state.throwable.printStackTrace()
    Text(
        text = "Error:\n${state.throwable.message}\n\nAPI Key: ***${
            BuildConfig.GEMINI_API_KEY.takeLast(
                3
            )
        }"
    )
}

@PreviewLightDark
@Composable
private fun Preview() {
    LocalAiTheme {
        ErrorView(State.Error(IllegalStateException("IllegalStateException")))
    }
}