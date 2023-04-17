import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.AwtWindow
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.FileDialog
import java.awt.Frame
import java.io.File

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
    var isFileChooserOpen by remember { mutableStateOf(false) }
    if (isFileChooserOpen) {
        FileDialog(onCloseRequest = {
            isFileChooserOpen = false
            it?.map { file ->
                println("Result ${file.absolutePath}")
                ExcelUtil.readLxnExcel1(file.absolutePath)

            }
        })
    }
    MaterialTheme {
        Column {
            Button(onClick = {
                isFileChooserOpen = true
            }) {
                Text("项目1")
            }
        }
    }
}

@Composable
private fun FileDialog(
    parent: Frame? = null, onCloseRequest: (result: Array<File>?) -> Unit
) = AwtWindow(
    create = {
        object : FileDialog(parent, "Choose a file", LOAD) {
            override fun setVisible(value: Boolean) {
                super.setVisible(value)
                if (value) {
                    onCloseRequest(files)
                }
            }
        }
    }, dispose = FileDialog::dispose
)


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
