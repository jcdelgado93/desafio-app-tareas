package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import cl.talentodigital.desarioaplicaciondetareas.databinding.DialogTareaBinding
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.LocalTareasRepository
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.TareasMapper
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.TareasRepository
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.TareasUseCase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AgregarTareaDialogFragment : DialogFragment() {

    private lateinit var binding: DialogTareaBinding
    private lateinit var tareasUseCase: TareasUseCase
    private lateinit var repository: TareasRepository
    private val mapper = TareasMapper()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            binding = DialogTareaBinding.inflate(LayoutInflater.from(context))
            builder.setView(binding.root)
            builder.setPositiveButton("Guardar") { dialogInterface: DialogInterface, i: Int ->
                setupDependencies()
                guardarUseCase()
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun setupDependencies() {
        repository = LocalTareasRepository(requireContext(), mapper)
        tareasUseCase = TareasUseCase(repository)
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
        Toast.makeText(requireContext(), "Tarea guardada", Toast.LENGTH_LONG).show()
    }

    private fun handleError(error: Throwable) {
        Toast.makeText(requireContext(), "Error: {${error.message}}", Toast.LENGTH_SHORT).show()
    }
}