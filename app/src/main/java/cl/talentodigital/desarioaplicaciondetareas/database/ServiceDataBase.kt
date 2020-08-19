package cl.talentodigital.desarioaplicaciondetareas.database

import android.content.Context
import androidx.room.Room

private const val DATA_BASE_NAME = "desafio-tareas.db"
class ServiceDataBase(
    applicationContext: Context
) {
    private val db = Room.databaseBuilder(
        applicationContext,
        TareasDataBase::class.java,
        DATA_BASE_NAME
    ).build()

    fun getDB() = db
}