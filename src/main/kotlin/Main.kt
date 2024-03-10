import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import service.GameService
import ui.screen.Home

@Composable
@Preview
fun App() {
    MaterialTheme {
        val gameService = remember { GameService() }
        Navigator(Home(
            gameService = gameService
        ))
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Secret Role Tracker") {
        App()
    }
}
