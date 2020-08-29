package cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain


class BorrarTareasUseCase(
    private val repository: TareasRepository
) {
    fun borrar() = repository.borrarTareas()
}