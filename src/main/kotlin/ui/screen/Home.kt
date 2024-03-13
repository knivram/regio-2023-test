package ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import service.GameService
import ui.components.GameStats
import ui.components.UserList

class Home : Screen {

    @Composable
    override fun Content() {
        Column {
            Button(
                onClick = GameService::startOrReset,
                enabled = GameService.PlayerRepository.getSize() >= 5
            ) {
                if (GameService.gameStarted) {
                    Text("New Game")
                } else {
                    Text("Start Game")
                }
            }
            Row {
                UserList()
                Spacer(modifier = Modifier.width(56.dp))
                GameStats()
            }
        }
    }
}
