package org.sharad.emify.core.util

import androidx.compose.runtime.Composable

@Composable
expect fun ImagePicker(
    onImagePicked: (ByteArray?) -> Unit
)
