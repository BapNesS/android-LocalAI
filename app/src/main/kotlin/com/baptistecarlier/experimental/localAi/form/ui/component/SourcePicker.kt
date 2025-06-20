package com.baptistecarlier.experimental.localAi.form.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.baptistecarlier.experimental.localAi.common.theme.LocalAiTheme
import com.baptistecarlier.experimental.localAi.form.data.repository.Source
import kotlin.enums.EnumEntries

@Composable
internal fun SourcePicker(
    radioOptions: EnumEntries<Source>,
    source: Source,
    onChange: (Source) -> Unit
) {
    Column(Modifier.selectableGroup()) {
        radioOptions.forEach { option ->
            val selected = source == option
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = selected,
                        onClick = { onChange(option) },
                        role = Role.RadioButton
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = selected,
                    onClick = { onChange(option) }
                )
                Text(text = option.name)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    LocalAiTheme {
        SourcePicker(
            radioOptions = Source.entries,
            source = Source.entries.first(),
            onChange = {}
        )
    }
}
