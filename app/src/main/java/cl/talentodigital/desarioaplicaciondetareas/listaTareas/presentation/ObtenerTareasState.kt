package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tareas

sealed class ObtenerTareasState(
    open val result: Tareas? = null,
    open val error: Throwable? = null
) {
    object LoadingStateObtener : ObtenerTareasState()
    data class Complete(override val result: Tareas?): ObtenerTareasState(result = result)
    data class Error(override val error: Throwable?): ObtenerTareasState(error = error)
}