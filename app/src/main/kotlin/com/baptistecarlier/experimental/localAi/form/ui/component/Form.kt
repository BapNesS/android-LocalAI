package com.baptistecarlier.experimental.localAi.form.ui.component

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baptistecarlier.experimental.localAi.R
import com.baptistecarlier.experimental.localAi.common.theme.LocalAiTheme
import com.baptistecarlier.experimental.localAi.form.data.repository.Source
import com.baptistecarlier.experimental.localAi.form.data.model.FormAnswer
import com.baptistecarlier.experimental.localAi.form.ui.viewModel.State

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun Form(
    modifier: Modifier = Modifier,
    state: State<FormAnswer>,
    onRequest: (source: Source, prompt: String) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        var prompt by remember { mutableStateOf("Give me an amazing cake recipe") }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = prompt,
            onValueChange = { prompt = it },
            label = { Text(stringResource(R.string.form_placeholder)) },
        )

        var source by remember { mutableStateOf(Source.entries.first()) }
        SourcePicker(
            radioOptions = Source.entries,
            source = source
        ) {
            source = it
        }

        Button(
            modifier = Modifier.align(End),
            enabled = prompt.isNotEmpty() && state !is State.Loading,
            shapes = ButtonDefaults.shapes(),
            onClick = { onRequest(source, prompt) },
        ) {
            Text(text = stringResource(R.string.form_button_request))
        }

        Crossfade(
            modifier = Modifier.fillMaxSize(),
            targetState = state,
            label = "Form State Transition"
        ) { state ->
            when (state) {
                is State.Loading -> LoadingView()
                is State.Error -> ErrorView(state)
                is State.Success -> SuccessView(source, state)
                else -> {
                    /* Nothing else matters */
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    LocalAiTheme {
        Form(state = State.Init) { _, _ -> }
    }
}