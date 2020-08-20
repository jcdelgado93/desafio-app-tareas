package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import androidx.recyclerview.widget.RecyclerView
import cl.talentodigital.desarioaplicaciondetareas.databinding.ItemTareaBinding
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea

//class TareasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
class TareasViewHolder(private val binding : ItemTareaBinding) : RecyclerView.ViewHolder(binding.root) {

    //private val binding = ItemTareaBinding.bind(itemView)

    fun bind(tarea: Tarea) {
        binding.apply {
            tvTarea.text = tarea.tarea
        }
    }
}
