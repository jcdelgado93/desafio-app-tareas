package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

sealed class BorrarTareasState(
  open val error: Throwable? = null
) {
    object LoadingStateBorrar : BorrarTareasState()
    object Complete: BorrarTareasState()
    data class Error(override val error: Throwable?): BorrarTareasState(error = error)
}