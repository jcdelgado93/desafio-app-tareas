package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.BorrarTareasUseCase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.GuardarTareaUseCase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TareasViewModel(
    private val guardarTareaUseCase: GuardarTareaUseCase,
    private val borrarTareasUseCase: BorrarTareasUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<TareasState>()
    private val compositeDisposable = CompositeDisposable()

    fun getLiveData() = liveData

    fun guardarTarea(tarea: Tarea) {
        liveData.postValue(TareasState.LoadingState)
        compositeDisposable.add(guardarTareaUseCase
            .guardar(tarea)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> handleResult(result) },
                { error -> handleError(error) }
            )
        )
    }

    private fun handleResult(result: Boolean) {
        liveData.postValue(TareasState.Complete(result))
    }

    private fun handleError(error: Throwable) {
        liveData.postValue(TareasState.Error(error))
    }

    fun borrarTareas(){
        liveData.postValue(TareasState.LoadingState)
        compositeDisposable.add(
            borrarTareasUseCase
                .borrar()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { handleCompletion() },
                    { error -> handleError(error) }
                )
        )
    }

    private fun handleCompletion() {
        liveData.postValue(TareasState.BorradoCompleto)
    }
}