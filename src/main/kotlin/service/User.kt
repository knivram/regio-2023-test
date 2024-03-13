package service

import androidx.compose.ui.graphics.Color
import repository.Entity
import java.util.UUID

data class User(
    override val id: UUID,
    val name: String,
    val color: Color,
) : Entity