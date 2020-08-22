package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.GuardarTareaUseCase

class GuardarTareasViewModelFactory(
    private val guardarTareaUseCase: GuardarTareaUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass
            .getConstructor(GuardarTareaUseCase::class.java)
            .newInstance(guardarTareaUseCase)
    }
}