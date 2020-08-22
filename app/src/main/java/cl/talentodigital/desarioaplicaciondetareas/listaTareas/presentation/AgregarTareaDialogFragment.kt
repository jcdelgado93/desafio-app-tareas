package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cl.talentodigital.desarioaplicaciondetareas.databinding.DialogTareaBinding
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.LocalTareasRepository
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.TareasMapper
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.TareasRepository
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.TareasUseCase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AgregarTareaDialogFragment : DialogFragment() {

    private lateinit var binding: DialogTareaBinding
    private lateinit var tareasUseCase: TareasUseCase
    private lateinit var repository: TareasRepository
    private val mapper = TareasMapper()
    private lateinit var tareasViewModel: TareasViewModel
    private lateinit var tareasViewModelFactory: TareasViewModelFactory

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            binding = DialogTareaBinding.inflate(LayoutInflater.from(context))
            builder.setView(binding.root)
            builder.setPositiveButton("Guardar") { dialogInterface: DialogInterface, i: Int ->
                setupDependencies()
                setupLiveData()
                guardarUseCase()
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun setupDependencies() {
        repository = LocalTareasRepository(requireContext(), mapper)
        tareasUseCase = TareasUseCase(repository)
        tareasViewModelFactory = TareasViewModelFactory(tareasUseCase)
        tareasViewModel = ViewModelProvider(this, tareasViewModelFactory)
            .get(TareasViewModel::class.java)
    }

    private fun setupLiveData() {
        tareasViewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer { state ->
                handleState(state)
            }
        )
    }

    private fun handleState(state: TareasState?) {
        when(state) {
            is TareasState.LoadingState -> mostrarCargando()
            is TareasState.Complete -> guardarTarea()
            is TareasState.Error -> mostrarError()
        }
    }

    private fun mostrarCargando() {
        Toast.makeText(context, "Cargando...", Toast.LENGTH_SHORT).show()
    }

    private fun guardarTarea() {
        Toast.makeText(context, "Tarea guardada", Toast.LENGTH_SHORT).show()
    }

    private fun mostrarError() {
        Toast.makeText(context, "Algo salio mal", Toast.LENGTH_SHORT).show()
    }

    private fun guardarUseCase() {
        compositeDisposable.add(
            tareasUseCase.guardar(getText())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {result -> handleReults(result)},
                    {error -> handleError(error)}
                )
        )
    }

    private fun getText(): Tarea {
        return Tarea(
            getTextValue(binding.etIngresoTarea)
        )
    }

    fun getTextValue(textInputEditText: TextInputEditText): String {
        return textInputEditText.text.toString()
    }

    private fun handleReults(result: Boolean?) {
        Toast.makeText(context, "Tarea guardada", Toast.LENGTH_LONG).show()
    }

    private fun handleError(error: Throwable) {
        Toast.makeText(requireContext(), "Error: {${error.message}}", Toast.LENGTH_SHORT).show()
    }
}