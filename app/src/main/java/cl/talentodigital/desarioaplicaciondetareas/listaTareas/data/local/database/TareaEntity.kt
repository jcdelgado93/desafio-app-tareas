package cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tareas_DB")
data class TareaEntity(
    @ColumnInfo(name = "tarea") val tarea: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}