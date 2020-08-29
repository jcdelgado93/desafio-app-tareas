package cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TareasDao {
    @Query("SELECT * FROM tareasDB")
    fun getAll(): Single<List<TareaEntity>>

    @Insert
    fun insertTask(tareaEntity: TareaEntity): Single<Long>

    /*@Delete
    fun deleteTasks(vararg tareaEntity: TareaEntity)*/

    @Query("DELETE FROM tareasDB")
    fun deleteAll(): Completable

    /*If want to delete an entry from the the table in Room
    simply call this function,
        @Delete
        void delete(MyModel model);

    Update: And if you want to delete complete table, call below function,
        @Query("DELETE FROM MyModel")
        void delete();
    Note: Here MyModel is a Table Name.*/

    /*@Delete
    fun deleteAll(tareasDB tarea)*/
}