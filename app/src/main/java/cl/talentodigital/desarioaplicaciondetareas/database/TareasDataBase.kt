package cl.talentodigital.desarioaplicaciondetareas.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.database.TareasDao
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.database.TareaEntity

@Database(entities = [TareaEntity::class], version = 1)
abstract class TareasDataBase : RoomDatabase() {
    abstract fun tareasDao(): TareasDao
}