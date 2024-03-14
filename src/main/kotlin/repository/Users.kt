package repository

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column

object Users: UUIDTable() {
    val name: Column<String> = text("name")
}