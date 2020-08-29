package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.BorrarTareasUseCase

class BorrarTareasViewModelFactory(
    private val borrarTareasUseCase: BorrarTareasUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass
            .getConstructor(BorrarTareasUseCase::class.java)
            .newInstance(borrarTareasUseCase)
    }
}