package cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain

import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea

class GuardarTareaUseCase(
    private val repository: TareasRepository
) {
    fun guardar(tarea: Tarea) = repository.guardarTarea(tarea)
}