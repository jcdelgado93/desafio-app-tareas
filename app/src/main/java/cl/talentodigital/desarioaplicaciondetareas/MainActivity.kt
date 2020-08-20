package cl.talentodigital.desarioaplicaciondetareas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation.AgregarTareaDialogFragment

class MainActivity : AppCompatActivity() {

    private lateinit var dialogo: AgregarTareaDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dialogo = AgregarTareaDialogFragment()
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
        TODO("Not yet implemented")
    }
}