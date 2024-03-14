package repository

import androidx.compose.runtime.mutableStateMapOf
import java.util.UUID

abstract class Repository<T : Entity> {
    private var entities = mutableStateMapOf<UUID, T>()

    fun insertOnStartUp(dbEntries: List<T>) {
        dbEntries.forEach {
            entities[it.id] = it
        }
    }
    fun getSize(): Int = entities.size
    fun getAll(): List<T> = entities.values.toList()
    fun getOne(id: UUID): T? = entities[id]
    fun new(newEntity: T) {
        newEntity.insert()
        entities[newEntity.id] = newEntity
    }
    fun remove(id: UUID) {
        entities.remove(id)
    }
    fun clear() {
        entities.clear()
    }
}