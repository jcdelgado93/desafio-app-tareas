package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cl.talentodigital.desarioaplicaciondetareas.R
import cl.talentodigital.desarioaplicaciondetareas.databinding.FragmentTareasBinding
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.LocalTareasRepository
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.TareasMapper
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.TareasRepository
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.ObtenerTareasUseCase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tareas

class TareasFragment : Fragment(R.layout.fragment_tareas) {

    private lateinit var binding: FragmentTareasBinding
    private lateinit var tareasAdapter: TareasAdapter
    private lateinit var obtenerTareaUseCase: ObtenerTareasUseCase
    private lateinit var repository: TareasRepository
    private lateinit var obtenerTareasViewModel: ObtenerTareasViewModel
    private lateinit var obtenerTareasViewModelFactory: ObtenerTareasViewModelFactory
    private val mapper = TareasMapper()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        setupLiveData()
        bindView(view)
        setupRecyclerView()
        obtenerViewModel()
    }

    private fun setupDependencies() {
        repository = LocalTareasRepository(requireContext(), mapper)
        obtenerTareaUseCase = ObtenerTareasUseCase(repository)
        obtenerTareasViewModelFactory = ObtenerTareasViewModelFactory(obtenerTareaUseCase)
        obtenerTareasViewModel = ViewModelProvider(this, obtenerTareasViewModelFactory)
            .get(ObtenerTareasViewModel::class.java)
    }

    private fun setupLiveData() {
        obtenerTareasViewModel.getLiveData()
            .observe(
                this,
                Observer { state ->
                    handleState(state)
                }
            )
    }

    private fun handleState(state: ObtenerTareasState?) {
        when(state) {
            is ObtenerTareasState.LoadingStateObtener -> mostrarCargando()
            is ObtenerTareasState.Complete -> state.result?.let { mostrarTareas(it) }
            is ObtenerTareasState.Error -> state.error?.let { mostrarError(it) }
        }
    }

    private fun mostrarCargando() {
        Toast.makeText(requireContext(), "Cargando...", Toast.LENGTH_SHORT).show()
    }

    private fun mostrarTareas(result: Tareas) {
        tareasAdapter = TareasAdapter(result.listaTareas)
        binding.rvTareas.adapter = tareasAdapter
    }

    private fun mostrarError(error: Throwable) {
        Toast.makeText(requireContext(), "Error: {${error.message}", Toast.LENGTH_SHORT).show()
    }

    private fun bindView(view: View) {
        binding = FragmentTareasBinding.bind(view)
    }

    private fun setupRecyclerView() {
        binding.apply {
            rvTareas.setHasFixedSize(true)
            rvTareas.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun obtenerViewModel() {
        obtenerTareasViewModel.obtenerTareas()
    }
}