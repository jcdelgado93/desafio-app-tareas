package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

sealed class GuardarTareasState(
    open val result: Boolean? = null,
    open val error: Throwable? = null
) {
    object LoadingStateGuardar : GuardarTareasState()
    data class Complete(override val result: Boolean?): GuardarTareasState(result = result)
    data class Error(override val error: Throwable?): GuardarTareasState(error = error)
}