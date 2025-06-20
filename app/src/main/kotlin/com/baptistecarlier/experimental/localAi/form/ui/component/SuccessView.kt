package com.baptistecarlier.experimental.localAi.form.ui.component

import android.content.ClipData
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ButtonGroup
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.toClipEntry
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.baptistecarlier.experimental.localAi.R
import com.baptistecarlier.experimental.localAi.common.theme.LocalAiTheme
import com.baptistecarlier.experimental.localAi.form.data.model.FormAnswer
import com.baptistecarlier.experimental.localAi.form.data.repository.Source
import com.baptistecarlier.experimental.localAi.form.ui.viewModel.State
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun SuccessView(
    source: Source,
    state: State.Success<FormAnswer>
) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        FlowRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            SuccessMetadata(stringResource(R.string.metadata_source, source.name))
            state.value.durationInMillis?.let {
                SuccessMetadata(stringResource(R.string.metadata_duration, it))
            }
        }
        Surface(
            modifier = Modifier.fillMaxWidth(),
            tonalElevation = 1.dp,
            shape = RoundedCornerShape(32.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // Content
                Text(text = state.value.content)

                // Actions
                val clipboard = LocalClipboard.current
                val copyDoneText = stringResource(R.string.copy_to_clipboard_done)
                val context = LocalContext.current
                FlowRow(
                    modifier = Modifier.align(End),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                clipboard.setClipEntry(
                                    ClipData.newPlainText(copyDoneText, state.value.content)
                                        .toClipEntry()
                                )
                            }
                        },
                    ) {
                        Icon(
                            Icons.Default.ContentCopy,
                            contentDescription = stringResource(R.string.copy_to_clipboard)
                        )
                    }

                    IconButton(
                        onClick = {
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, state.value.content)
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, null)
                            context.startActivity(shareIntent)
                        }
                    ) {
                        Icon(
                            Icons.Default.Share,
                            contentDescription = stringResource(R.string.share)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SuccessMetadata(content: String) {
    FilterChip(
        selected = true,
        onClick = { /* Handle source click if needed */ },
        label = { Text(text = content) },
    )
}

@PreviewLightDark
@Composable
private fun Preview() {
    LocalAiTheme {
        SuccessView(
            Source.GeminiLocal,
            State.Success(
                FormAnswer(
                    content = "Mix all ingredients and bake at 350°F for 30 minutes.",
                    durationInMillis = 1500L
                )
            )
        )
    }
}

