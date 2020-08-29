package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.BorrarTareasUseCase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.GuardarTareaUseCase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.ObtenerTareasUseCase

class TareasViewModelFactory(
    private val guardarTareaUseCase: GuardarTareaUseCase,
    private val borrarTareasUseCase: BorrarTareasUseCase,
    private val obtenerTareasUseCase: ObtenerTareasUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass
            .getConstructor(GuardarTareaUseCase::class.java)
            .newInstance(guardarTareaUseCase, borrarTareasUseCase, obtenerTareasUseCase)
    }
}