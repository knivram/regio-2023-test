package ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import service.GameService
import ui.components.UserList

data class Home(
    val gameService: GameService
) : Screen {

    @Composable
    override fun Content() {
        Column {
            Button(
                onClick = gameService::startOrReset
            ) {
                if (gameService.gameStarted) {
                    Text("New Game")
                } else {
                    Text("Start Game")
                }
            }
            Row {
                UserList(gameService)
            }
        }
    }
}
