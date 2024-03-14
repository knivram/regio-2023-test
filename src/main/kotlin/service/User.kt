package service

import androidx.compose.ui.graphics.Color
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import repository.Entity
import repository.Users
import java.util.UUID

data class User(
    override val id: UUID,
    val name: String,
    val color: Color,
) : Entity {
    override fun insert() {
        val database = GameService.database
        transaction(database) {
            Users.insert {
                it[Users.id] = this@User.id
                it[Users.name] = this@User.name
            }
        }
    }
}