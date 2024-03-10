package ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import service.GameService
import service.MAX_CARD_B
import service.MAX_CARD_R

@Composable
fun GameStats(
    gameService: GameService
) {
    Column {
        Row {
            Text("Round")
            Spacer(modifier = Modifier.width(16.dp))
            Text(gameService.roundNumer.toString())
            Spacer(modifier = Modifier.width(32.dp))
            Column {
                Text("Blue: ${gameService.cardsB}/$MAX_CARD_B")
                Spacer(modifier = Modifier.height(5.dp))
                Text("Blue: ${gameService.cardsR}/$MAX_CARD_R")
            }
        }
    }
}