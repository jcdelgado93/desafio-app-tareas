package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.TareasUseCase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TareasViewModel(
    private val tareasUseCase: TareasUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<TareasState>()
    private val compositeDisposable = CompositeDisposable()

    fun getLiveData() = liveData

    fun saveTask(tarea: Tarea) {
        liveData.postValue(TareasState.LoadingState)
        compositeDisposable.add(tareasUseCase
            .guardar(tarea)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> handleResult(result) },
                { error -> handleError(error) }
            )
        )
    }

    private fun handleResult(result: Tarea) {
        liveData.postValue(TareasState.Complete(result))
    }

    private fun handleError(error: Throwable) {
        liveData.postValue(TareasState.Error(error))
    }
}