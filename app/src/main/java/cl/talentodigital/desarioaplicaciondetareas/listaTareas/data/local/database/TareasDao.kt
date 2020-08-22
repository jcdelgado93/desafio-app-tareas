package cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea
import io.reactivex.Single

@Dao
interface TareasDao {
    @Query("SELECT * FROM tareasDB")
    fun getAll(): Single<List<TareaEntity>>

    @Insert
    fun insertTask(tareaEntity: TareaEntity): Single<Long>

    /*@Delete
    fun deleteAll(vararg tareas: Tarea)*/
}