package com.example.activitystarwapp.presentation.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitystarwapp.R
import com.example.activitystarwapp.data.model.PlanetsModel
import com.example.activitystarwapp.databinding.ActivityTodosplanetasBinding
import com.example.activitystarwapp.presentation.adapter.PlanetAdapter
import com.example.activitystarwapp.presentation.viewmodel.TodosPlanetasViewModel

class TodosPlanetasActivity : BaseActivity() {
    private lateinit var binding : ActivityTodosplanetasBinding
    private lateinit var viewModel : TodosPlanetasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTodosplanetasBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initlizeViews()

        viewModel = ViewModelProvider(this).get(TodosPlanetasViewModel::class.java)
        viewModel.setUpList()

        initObservers()
        getData()
    }

    private fun initlizeViews() {
        setTitleActivity(R.string.planetas)
        setIconActivity(R.drawable.planet)
    }

    private fun initObservers() {
        viewModel.planetList.observe(this) {
            setUpAdapterPlanet(it.results)
        }
    }

    private fun getData() {
        viewModel.getPlanets()
    }

    private fun setUpAdapterPlanet(listPlanet: List<PlanetsModel.Result>) {
        val adapter = PlanetAdapter(listPlanet)
        binding.tvResultcounttodosplanet.text = getString(R.string.resultado) + listPlanet.count()
        binding.rvPlanets.adapter = adapter
        binding.rvPlanets.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        loadCompleted()
    }
}