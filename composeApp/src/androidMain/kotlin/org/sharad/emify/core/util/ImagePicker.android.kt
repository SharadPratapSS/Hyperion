package org.sharad.emify.core.util


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import java.io.InputStream

@Composable
actual fun ImagePicker(
    onImagePicked: (ByteArray?) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            scope.launch {
                uri?.let {
                    val inputStream: InputStream? = context.contentResolver.openInputStream(it)
                    val bytes = inputStream?.readBytes()
                    onImagePicked(bytes)
                } ?: onImagePicked(null)
            }
        }
    )

    // Launch immediately when Composable is called
    launcher.launch("image/*")
}
