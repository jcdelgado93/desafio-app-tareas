package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.BorrarTareasUseCase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.GuardarTareaUseCase

class TareasViewModelFactory(
    private val guardarTareaUseCase: GuardarTareaUseCase,
    private val borrarTareasUseCase: BorrarTareasUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass
            .getConstructor(GuardarTareaUseCase::class.java)
            .newInstance(guardarTareaUseCase, borrarTareasUseCase)
    }
}