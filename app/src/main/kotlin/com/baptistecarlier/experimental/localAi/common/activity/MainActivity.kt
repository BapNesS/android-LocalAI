package com.baptistecarlier.experimental.localAi.common.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.baptistecarlier.experimental.localAi.common.theme.LocalAiTheme
import com.baptistecarlier.experimental.localAi.form.ui.component.FormScreen

internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LocalAiTheme {
                FormScreen()
            }
        }
    }
}