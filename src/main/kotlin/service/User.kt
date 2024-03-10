package service

import androidx.compose.ui.graphics.Color
import java.util.UUID

data class User(
    val id: UUID,
    val name: String,
    val color: Color,
) {
    constructor(name: String) : this(UUID.randomUUID(), name, generateRandomColor())
}
