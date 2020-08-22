package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.GuardarTareaUseCase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GuardarTareasViewModel(
    private val guardarTareaUseCase: GuardarTareaUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<GuardarTareasState>()
    private val compositeDisposable = CompositeDisposable()

    fun getLiveData() = liveData

    fun guardarTarea(tarea: Tarea) {
        liveData.postValue(GuardarTareasState.LoadingStateGuardar)
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
        liveData.postValue(GuardarTareasState.Complete(result))
    }

    private fun handleError(error: Throwable) {
        liveData.postValue(GuardarTareasState.Error(error))
    }
}