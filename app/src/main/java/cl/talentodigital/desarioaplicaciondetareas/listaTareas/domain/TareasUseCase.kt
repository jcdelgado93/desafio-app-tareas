package cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain

import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea

class TareasUseCase(
    private val repository: TareasRepository
) {
    fun obtener() = repository.obtenerTareas()
    fun guardar(tarea: Tarea) = repository.guardarTarea(tarea)
    /*fun borrar(tareas: Tarea) = repository.borrarTareas(tareas)*/
}