package service

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import utils.ExceptionCode
import java.util.UUID
import kotlin.random.Random

const val MAX_PLAYER = 10
const val MAX_CARD_B = 5
const val MAX_CARD_R = 6

object GameService {
    var gameStarted by mutableStateOf(false)

    private var players = mutableStateMapOf<UUID, User>()

    private var events = mutableStateListOf<Event>()

    fun startOrReset() {
        if (gameStarted) {
            gameStarted = false
            players.clear()
            events.clear()
        } else if (players.size >= 5) {
            gameStarted = true
        }
    }

    fun addPlayer(name: String) {
        if (players.size == MAX_PLAYER) {
            throw RuntimeException(ExceptionCode.MAX_USER.name)
        } else if (players.values.find { it.name == name } != null) {
            throw RuntimeException(ExceptionCode.USER_EXISTS.name)
        } else if (gameStarted) {
            throw RuntimeException(ExceptionCode.GAME_ALREADY_STARTED.name)
        }

        val newUser = User(
            id = UUID.randomUUID(),
            name = name,
            color = generateRandomColor()
        )
        players[newUser.id] = newUser
    }

    fun getPlayers() = players.values.toList().sortedBy { it.name }

    fun removePlayer(id: UUID) {
        players.remove(id)
    }

    fun getEvents() = events.toList()
    fun addEvent(event: Event) {
        events.add(event)
    }

    fun getPlayer(id: UUID): User? = players[id]
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