package cl.talentodigital.desarioaplicaciondetareas

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.LocalTareasRepository
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.TareasMapper
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.BorrarTareasUseCase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.TareasRepository
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.GuardarTareaUseCase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation.AgregarTareaDialogFragment
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation.TareasState
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation.TareasViewModel
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation.TareasViewModelFactory

class MainActivity : AppCompatActivity(), AgregarTareaDialogFragment.AgregarTareaCallBack {

    private lateinit var dialogo: AgregarTareaDialogFragment
    private lateinit var guardarTareaUseCase: GuardarTareaUseCase
    private lateinit var borrarTareasUseCase: BorrarTareasUseCase
    private lateinit var repository: TareasRepository
    private val mapper = TareasMapper()
    private lateinit var tareasViewModel: TareasViewModel
    private lateinit var tareasViewModelFactory: TareasViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dialogo = AgregarTareaDialogFragment(this)

        setupDependencies()
        setupLiveData()
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
        repository = LocalTareasRepository(this, mapper)
        guardarTareaUseCase = GuardarTareaUseCase(repository)
        borrarTareasUseCase = BorrarTareasUseCase(repository)
        tareasViewModelFactory = TareasViewModelFactory(guardarTareaUseCase, borrarTareasUseCase)
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
        when(state) {
            is TareasState.LoadingState -> mostrarCargando()
            is TareasState.Complete -> guardarTarea()
            is TareasState.Error -> mostrarError()
        }
    }

    private fun mostrarCargando() {
        Toast.makeText(this, "Cargando...", Toast.LENGTH_SHORT).show()
    }

    private fun guardarTarea() {
        Toast.makeText(this, "Tarea guardada", Toast.LENGTH_SHORT).show()
    }

    private fun mostrarError() {
        Toast.makeText(this, "Algo salio mal", Toast.LENGTH_SHORT).show()
    }

    override fun procesarTarea(text: String) {
        tareasViewModel.guardarTarea(Tarea(text))
    }
}