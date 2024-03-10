import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import ui.screen.Home

@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator(Home())
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Regio 2023") {
        App()
    }
}
