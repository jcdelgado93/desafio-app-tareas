package cl.talentodigital.desarioaplicaciondetareas.listaTareas.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cl.talentodigital.desarioaplicaciondetareas.R
import cl.talentodigital.desarioaplicaciondetareas.databinding.ActivityMainBinding
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.LocalTareasRepository
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.TareasMapper
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.TareasUseCase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tareas
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation.TareasState
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation.TareasViewModel
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation.TareasViewModelFactory

class MainActivity : AppCompatActivity(), AgregarTareaDialogFragment.AgregarTareaCallBack {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dialogo: AgregarTareaDialogFragment
    private lateinit var tareasAdapter: TareasAdapter
    private lateinit var tareasViewModel: TareasViewModel
    private lateinit var tareasViewModelFactory: TareasViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialogo = AgregarTareaDialogFragment(this)

        setupDependencies()
        setupLiveData()
        setupRecyclerView()
        obtenerViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.acciones_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_agregar -> {
                mostrarDialogoAgregar()
                true
            }
            R.id.action_borrar -> {
                borrarTodo()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun mostrarDialogoAgregar() {
        dialogo.show(supportFragmentManager, "String")
    }

    private fun borrarTodo() {
        tareasViewModel.borrarTareas()
    }

    private fun setupDependencies() {
        val repository = LocalTareasRepository(this, TareasMapper())
        tareasViewModelFactory = TareasViewModelFactory(
            TareasUseCase(
                repository
            )
        )

        tareasViewModel = ViewModelProvider(this, tareasViewModelFactory)
            .get(TareasViewModel::class.java)
    }

    private fun setupLiveData() {
        tareasViewModel.getLiveData().observe(
            this,
            Observer { state ->
                handleState(state)
            }
        )
    }

    private fun handleState(state: TareasState?) {
        when (state) {
            is TareasState.LoadingState -> mostrarCargando()
            is TareasState.BorrarTareas -> borrarTareas()
            is TareasState.TareaGuardada -> guardarTarea()
            is TareasState.ObtencionDeTareas -> state.resultObtener?.let { mostrarTareas(it) }
            is TareasState.Error -> state.error?.let { mostrarError(it) }
        }
    }

    private fun mostrarCargando() {
        Toast.makeText(this, "Cargando...", Toast.LENGTH_SHORT).show()
    }

    private fun borrarTareas() {
        Toast.makeText(this, "Tareas borradas", Toast.LENGTH_SHORT).show()
    }

    private fun guardarTarea() {
        Toast.makeText(this, "Tarea guardada", Toast.LENGTH_SHORT).show()
    }

    private fun mostrarTareas(result: Tareas) {
        tareasAdapter = TareasAdapter(result.listaTareas)
        binding.rvTareas.adapter = tareasAdapter
    }

    private fun mostrarError(error: Throwable) {
        Toast.makeText(this, "Error: {${error.message}", Toast.LENGTH_SHORT).show()
    }

    private fun setupRecyclerView() {
        binding.apply {
            rvTareas.setHasFixedSize(true)
            rvTareas.layoutManager = LinearLayoutManager(this@MainActivity)
            rvTareas.addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun obtenerViewModel() {
        tareasViewModel.obtenerTareas()
    }

    override fun procesarTarea(text: String) {
        tareasViewModel.guardarTarea(Tarea(text))
    }
}