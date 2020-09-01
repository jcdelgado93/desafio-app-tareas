package cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain

import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea

class TareasUseCase(
    private val repository: TareasRepository
) {
    fun borrar() = repository.borrarTareas()
    fun guardar(tarea: Tarea) = repository.guardarTarea(tarea)
    fun obtener() = repository.obtenerTareas()
}