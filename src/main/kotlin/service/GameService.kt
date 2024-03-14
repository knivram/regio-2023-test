package service

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import repository.Repository
import repository.Users
import utils.ExceptionCode
import java.util.UUID
import kotlin.random.Random

const val MAX_PLAYER = 10
const val MAX_CARD_B = 5
const val MAX_CARD_R = 6

object GameService {
    val database = Database.connect(
        "jdbc:mysql://localhost:3306/regio",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "password"
    )
    var gameStarted by mutableStateOf(false)

    init {
        transaction(database) {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(Users)
        }
    }

    object PlayerRepository : Repository<User>() {
        init {
            transaction(database) {
                val users = Users.selectAll()
                    .map { User(it[Users.id].value, it[Users.name], generateRandomColor()) }
                this@PlayerRepository.insertOnStartUp(users)
            }
        }

        fun new(name: String) {
            if (PlayerRepository.getSize() == MAX_PLAYER) {
                throw RuntimeException(ExceptionCode.MAX_USER.message)
            } else if (PlayerRepository.getAll().find { it.name == name } != null) {
                throw RuntimeException(ExceptionCode.USER_EXISTS.message)
            } else if (gameStarted) {
                throw RuntimeException(ExceptionCode.GAME_ALREADY_STARTED.message)
            }

            val newUser = User(
                id = UUID.randomUUID(),
                name = name,
                color = generateRandomColor()
            )
            PlayerRepository.new(newUser)
        }
    }

    object EventRepository : Repository<Event>()

    fun startOrReset() {
        if (gameStarted) {
            gameStarted = false
            PlayerRepository.clear()
            EventRepository.clear()
        } else if (PlayerRepository.getSize() >= 5) {
            gameStarted = true
        }
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