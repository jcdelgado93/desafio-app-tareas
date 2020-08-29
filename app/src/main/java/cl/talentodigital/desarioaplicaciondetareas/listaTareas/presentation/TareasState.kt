package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

sealed class TareasState(
    open val result: Boolean? = null,
    open val error: Throwable? = null
) {
    object LoadingState : TareasState()
    object BorradoCompleto : TareasState()
    data class Complete(override val result: Boolean?): TareasState(result = result)
    data class Error(override val error: Throwable?): TareasState(error = error)
}