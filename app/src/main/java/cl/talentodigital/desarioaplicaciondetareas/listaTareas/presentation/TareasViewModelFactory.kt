package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.TareasUseCase

class TareasViewModelFactory(
    private val tareasUseCase: TareasUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass
            .getConstructor(TareasUseCase::class.java)
            .newInstance(tareasUseCase)
    }
}