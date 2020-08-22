package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.ObtenerTareasUseCase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tareas
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ObtenerTareasViewModel(
    private val obtenerTareasUseCase: ObtenerTareasUseCase
): ViewModel() {

    private val liveData = MutableLiveData<ObtenerTareasState>()
    private val compositeDisposable = CompositeDisposable()

    fun getLiveData() = liveData

    fun obtenerTareas() {
        liveData.postValue(ObtenerTareasState.LoadingStateObtener)
        compositeDisposable.add(obtenerTareasUseCase
            .obtener()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> handleResult(result) },
                { error -> handleError(error) }
            )
        )
    }

    private fun handleResult(result: Tareas) {
        liveData.postValue(ObtenerTareasState.Complete(result))
    }

    private fun handleError(error: Throwable) {
        liveData.postValue(ObtenerTareasState.Error(error))
    }
}