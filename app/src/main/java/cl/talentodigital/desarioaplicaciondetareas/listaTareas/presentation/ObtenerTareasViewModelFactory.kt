package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.GuardarTareaUseCase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.ObtenerTareasUseCase

class ObtenerTareasViewModelFactory(
    private val obtenerTareasUseCase: ObtenerTareasUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass
            .getConstructor(ObtenerTareasUseCase::class.java)
            .newInstance(obtenerTareasUseCase)
    }

}