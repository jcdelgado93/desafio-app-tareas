package cl.talentodigital.desarioaplicaciondetareas.listaTareas.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cl.talentodigital.desarioaplicaciondetareas.R
import cl.talentodigital.desarioaplicaciondetareas.databinding.FragmentTareasBinding
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.LocalTareasRepository
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.data.local.TareasMapper
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.TareasRepository
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.TareasUseCase
import cl.talentodigital.desarioaplicaciondetareas.listaTareas.domain.model.Tarea
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TareasFragment : Fragment(R.layout.fragment_tareas) {

    private lateinit var binding: FragmentTareasBinding
    private lateinit var tareasAdapter: TareasAdapter
    private lateinit var tareasUseCase: TareasUseCase
    private lateinit var repository: TareasRepository
    private val mapper = TareasMapper()
    private val compositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        bindView(view)
        setupRecyclerView()
        callUseCase()
    }

    private fun setupDependencies() {
        repository = LocalTareasRepository(requireContext(), mapper)
        tareasUseCase = TareasUseCase(repository)
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

    private fun callUseCase() {
        compositeDisposable.add(tareasUseCase.guardar(obtenerTarea())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result -> handleReults(result)},
                {error -> handleError(error)}
            )
        )
    }

    private fun handleReults(result: Boolean?) {
        TODO("Not yet implemented")
    }

    private fun handleError(error: Throwable?) {
        TODO("Not yet implemented")
    }

    private fun obtenerTarea(): Tarea {
        return Tarea(
            getTextValue(binding.nombreTarea),
            getTextValue(binding.infoTarea)
        )
    }

    private fun getTextValue(textInputEditText: TextInputEditText): String {
        return textInputEditText.text.toString()
    }
}