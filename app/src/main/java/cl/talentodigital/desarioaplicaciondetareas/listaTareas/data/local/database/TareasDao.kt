package cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

@Dao
interface TareasDao {
    @Query("SELECT * FROM tareas_DB")
    fun obtenerTodo(): Single<List<TareaEntity>>

    @Insert
    fun guardarTarea(tareaEntity: TareaEntity): Single<Long>

    //En el caso que queramos hacer un
    //borrado total, se recomienda hacer una query SQL
    // con un método anotado con @SQL .
    @Query("SELECT * FROM tareas_DB")
    @Delete
    fun borrarTodo()
}