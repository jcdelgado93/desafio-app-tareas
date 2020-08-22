package cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain

class ObtenerTareasUseCase(
    private val repository: TareasRepository
) {
    fun obtener() = repository.obtenerTareas()
}