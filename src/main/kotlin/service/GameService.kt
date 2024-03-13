package service

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import repository.Repository
import utils.ExceptionCode
import java.util.UUID
import kotlin.random.Random

const val MAX_PLAYER = 10
const val MAX_CARD_B = 5
const val MAX_CARD_R = 6

object GameService {
    var gameStarted by mutableStateOf(false)

    val playerRepository = Repository<User>()

    val eventRepository = Repository<Event>()

    fun startOrReset() {
        if (gameStarted) {
            gameStarted = false
            playerRepository.clear()
            eventRepository.clear()
        } else if (playerRepository.getSize() >= 5) {
            gameStarted = true
        }
    }

    fun Repository<User>.new(name: String) {
        if (playerRepository.getSize() == MAX_PLAYER) {
            throw RuntimeException(ExceptionCode.MAX_USER.message)
        } else if (playerRepository.getAll().find { it.name == name } != null) {
            throw RuntimeException(ExceptionCode.USER_EXISTS.message)
        } else if (gameStarted) {
            throw RuntimeException(ExceptionCode.GAME_ALREADY_STARTED.message)
        }

        val newUser = User(
            id = UUID.randomUUID(),
            name = name,
            color = generateRandomColor()
        )
        playerRepository.new(newUser)
    }
}

// ChatGPT
fun generateRandomColor(): Color {
    val hue = Random.nextFloat() * 360 // 0-360 degrees
    val saturation = 0.5f + Random.nextFloat() * 0.5f // 50-100%
    val lightness = 0.3f + Random.nextFloat() * 0.4f // 30-70%
    val alpha = 1f // Fully opaque

    // Use the hsl method to create a color from the random values
    return Color.hsl(hue, saturation, lightness, alpha)
}