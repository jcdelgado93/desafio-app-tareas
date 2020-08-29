package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tareas

sealed class TareasState(
    open val resultGuardar: Boolean? = null,
    open val resultObtener: Tareas? = null,
    open val error: Throwable? = null
) {
    object LoadingState : TareasState()
    object BorrarTareas : TareasState()
    data class TareaGuardada(override val resultGuardar: Boolean?): TareasState(resultGuardar = resultGuardar)
    data class ObtencionDeTareas(override val resultObtener: Tareas?): TareasState(resultObtener = resultObtener)
    data class Error(override val error: Throwable?): TareasState(error = error)
}