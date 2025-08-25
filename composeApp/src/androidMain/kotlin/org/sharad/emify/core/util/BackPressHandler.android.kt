package org.sharad.emify.core.util

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
actual fun ChangeBackPress(enabled: Boolean, action: () -> Unit) {
    BackHandler(enabled = enabled, onBack = action)
}