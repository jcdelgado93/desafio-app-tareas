package cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain

import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tareas
import io.reactivex.Single

interface TareasRepository {
    fun obtenerTareas(): Single<Tareas>
    fun guardarTarea(tarea: Tarea): Single<Boolean>
    /*fun borrarTareas(tareas: Tarea)*/
}