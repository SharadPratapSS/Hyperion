package org.sharad.emify.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.sharad.emify.core.navigation.BaseNavigation
import org.sharad.emify.core.ui.theme.EmiFyTheme

@Composable
fun App() {
    EmiFyTheme{
        Scaffold{
            Box(modifier=Modifier.fillMaxSize().imePadding().background(Color(0xFFF7F7F7))){ BaseNavigation(padding = it) }
        }
    }
}