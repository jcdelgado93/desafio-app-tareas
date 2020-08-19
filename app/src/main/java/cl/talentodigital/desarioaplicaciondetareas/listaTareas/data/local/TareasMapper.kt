package cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local

import cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.database.TareaEntity
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tareas

class TareasMapper {

    fun mapDomainToRoom(tarea: Tarea): TareaEntity {
        return TareaEntity(
            tarea = tarea.tarea
        )
    }

    fun mapRoomToDomain(list: List<TareaEntity>): Tareas {
        return Tareas(list.map {
            Tarea(it.tarea)
        })
    }
}
