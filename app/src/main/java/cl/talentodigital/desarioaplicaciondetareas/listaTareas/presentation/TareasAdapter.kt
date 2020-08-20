package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.talentodigital.desarioaplicaciondetareas.databinding.ItemTareaBinding
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea

class TareasAdapter(
    private val tareas: List<Tarea>
) : RecyclerView.Adapter<TareasViewHolder>() {

    private lateinit var binding: ItemTareaBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareasViewHolder {
        binding = ItemTareaBinding.inflate(LayoutInflater.from(parent.context))
        //val inflater = LayoutInflater.from(parent.context)
        //val view: View = inflater.inflate(R.layout.item_tarea, parent, false)
        return TareasViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tareas.size
    }

    override fun onBindViewHolder(holder: TareasViewHolder, position: Int) {
        holder.bind(tareas[position])
    }
}