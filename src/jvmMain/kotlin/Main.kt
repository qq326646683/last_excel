import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.AwtWindow
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.alibaba.excel.util.DateUtils
import java.awt.FileDialog
import java.awt.Frame
import java.io.File
import java.util.*

@Composable
@Preview
fun App() {
    var savePath by remember { mutableStateOf("") }
    var isFileChooserOpen by remember { mutableStateOf(false) }
    if (isFileChooserOpen) {
        FileDialog(onCloseRequest = {
            isFileChooserOpen = false
            it?.map { file ->
                println("Result ${file.absolutePath}")
                savePath = file.absolutePath.replace(".", "_${DateUtils.format(Date())}.")
                ExcelUtil.readLxnExcel1(file.absolutePath, savePath)
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
            Text("生成文件: $savePath")
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
