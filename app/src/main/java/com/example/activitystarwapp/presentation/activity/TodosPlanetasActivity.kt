package com.example.activitystarwapp.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitystarwapp.data.model.PlanetsModel
import com.example.activitystarwapp.databinding.ActivityPlanetitemBinding
import com.example.activitystarwapp.databinding.ActivityTodospersonagemBinding
import com.example.activitystarwapp.databinding.ActivityTodosplanetasBinding
import com.example.activitystarwapp.presentation.adapter.PlanetAdapter
import com.example.activitystarwapp.presentation.viewmodel.TodosPersonagensViewModel
import com.example.activitystarwapp.presentation.viewmodel.TodosPlanetasViewModel

class TodosPlanetasActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTodosplanetasBinding
    private lateinit var viewModel : TodosPlanetasViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodosplanetasBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(TodosPlanetasViewModel::class.java)
        setContentView(binding.root)

        binding.imvVoltatodosplanet.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        viewModel.setUpList()

        initObservers()
        getData()
    }

    override fun onResume() {
        super.onResume()
        binding.pbLoadingrv.visibility = View.VISIBLE
    }

    private fun initObservers() {
        viewModel.planetList.observe(this) {
            updatePlanets(it.results)
        }
    }

    private fun getData() {
        binding.rvPlanets.adapter = null
        viewModel.getPlanets()
    }

    fun updatePlanets(listPlanet: List<PlanetsModel.Result>) {
        setUpAdapterPlanet(listPlanet)
        binding.pbLoadingrv.visibility = View.GONE
    }

    private fun setUpAdapterPlanet(listPlanet: List<PlanetsModel.Result>) {

        val adapter = PlanetAdapter(listPlanet)
        binding.rvPlanets.adapter = adapter
        binding.rvPlanets.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
}