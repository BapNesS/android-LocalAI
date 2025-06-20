package com.baptistecarlier.experimental.localAi.form.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.baptistecarlier.experimental.localAi.common.theme.LocalAiTheme

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun LoadingView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ContainedLoadingIndicator()
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    LocalAiTheme {
        LoadingView()
    }
}