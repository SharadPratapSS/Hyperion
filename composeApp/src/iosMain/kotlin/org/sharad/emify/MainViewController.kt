package org.sharad.emify

import androidx.compose.ui.window.ComposeUIViewController
import org.sharad.emify.app.App
import org.sharad.emify.core.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initializeKoin() }
) {
    App()
}