package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cl.talentodigital.desarioaplicaciondetareas.databinding.ItemTareaBinding
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea

class TareasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemTareaBinding.bind(itemView)

    fun bind(tarea: Tarea) {
        binding.apply {
            tvTitulo.text = tarea.tarea
        }
    }
}
