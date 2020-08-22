package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import cl.talentodigital.desarioaplicaciondetareas.databinding.DialogTareaBinding

class AgregarTareaDialogFragment(
    private val agregarTareaCallBack: AgregarTareaCallBack
) : DialogFragment() {

    private lateinit var binding: DialogTareaBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            binding = DialogTareaBinding.inflate(LayoutInflater.from(context))
            builder.setView(binding.root)
            builder.setPositiveButton("Guardar") { dialogInterface: DialogInterface, i: Int ->
                agregarTareaCallBack.procesarTarea(binding.etIngresoTarea.text.toString())
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    interface AgregarTareaCallBack {
        fun procesarTarea(text: String)
    }
}