package cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local

import android.content.Context
import cl.talentodigital.desarioaplicaciondetareas.database.ServiceDataBase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.TareasRepository
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tareas
import io.reactivex.Completable
import io.reactivex.Single

class LocalTareasRepository(
    applicationContext: Context,
    private val mapper: TareasMapper
): TareasRepository {

    private val database = ServiceDataBase(applicationContext)

    override fun obtenerTareas(): Single<Tareas> {
        return database
            .getDB()
            .tareasDao()
            .getAll()
            .map { list -> mapper.mapRoomToDomain(list) }
    }

    override fun guardarTarea(tarea: Tarea): Single<Boolean> {
        return database
            .getDB()
            .tareasDao()
            .insertTask(mapper.mapDomainToRoom(tarea))
            .map {
                it > 0
            }
    }

    override fun borrarTareas(): Completable {
        return database
            .getDB()
            .tareasDao()
            .deleteAll()
    }
}