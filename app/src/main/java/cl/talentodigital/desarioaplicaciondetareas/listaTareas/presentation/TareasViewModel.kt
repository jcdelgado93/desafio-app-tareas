package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.TareasUseCase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tareas
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TareasViewModel(
    private val tareasUseCase: TareasUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<TareasState>()
    private val compositeDisposable = CompositeDisposable()

    fun getLiveData() = liveData

    fun guardarTarea(tarea: Tarea) {
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

    fun borrarTareas() {
        liveData.postValue(TareasState.LoadingState)
        compositeDisposable.add(tareasUseCase
            .borrar()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { handleCompletion() },
                { error -> handleError(error) }
            )
        )
    }

    fun obtenerTareas() {
        liveData.postValue(TareasState.LoadingState)
        compositeDisposable.add(tareasUseCase
            .obtener()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> handleResult(result) },
                { error -> handleError(error) }
            )
        )
    }

    private fun handleResult(result: Boolean) {
        liveData.postValue(TareasState.TareaGuardada(result))
    }

    private fun handleResult(result: Tareas) {
        liveData.postValue(TareasState.ObtencionDeTareas(result))
    }

    private fun handleCompletion() {
        liveData.postValue(TareasState.BorrarTareas)
    }

    private fun handleError(error: Throwable) {
        liveData.postValue(TareasState.Error(error))
    }
}