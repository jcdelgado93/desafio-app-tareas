package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.BorrarTareasUseCase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BorrarTareasViewModel(
    private val borrarTareasUseCase: BorrarTareasUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<BorrarTareasState>()
    private val compositeDisposable = CompositeDisposable()

    fun getLiveData() = liveData

    fun borrarTareas() {
        liveData.postValue(BorrarTareasState.LoadingStateBorrar)
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
        liveData.postValue(BorrarTareasState.Complete)
    }

    private fun handleError(error: Throwable) {
        liveData.postValue(BorrarTareasState.Error(error))
    }

    /*apiClient.updateMyData(myUpdatedData)
    .subscribe( -> {
        // handle completion
    }, throwable -> {
        // handle error
    });*/

    /*private fun handleResult(result: Any) {
        liveData.postValue(BorrarTareasState.Complete(result))
    }

    private fun handleError(error: Any) {
        liveData.postValue(BorrarTareasState.Error(error))
    }*/
}