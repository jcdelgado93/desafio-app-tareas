package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea

sealed class TareasState(
    open val result: Tarea? = null,
    open val error: Throwable? = null
) {
    object LoadingState : TareasState()
    data class Complete(override val result: Tarea?): TareasState(result = result)
    data class Error(override val error: Throwable?): TareasState(error = error)
}