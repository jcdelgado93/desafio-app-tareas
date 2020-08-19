package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.talentodigital.desarioaplicaciondetareas.R
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea

class TareasAdapter(private val tareas: List<Tarea>) : RecyclerView.Adapter<TareasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareasViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_tarea, parent, false)
        return TareasViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tareas.size
    }

    override fun onBindViewHolder(holder: TareasViewHolder, position: Int) {
        holder.bind(tareas[position])
    }
}