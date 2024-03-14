package repository

import java.util.UUID

interface Entity {
    val id: UUID

    fun insert()
}