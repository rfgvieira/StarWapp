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
    lateinit var listPlanet: List<PlanetsModel.Result>


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
            //val fragment = supportFragmentManager.findFragmentById(R.id.fl_todosplanetas)
            //fragment.setUpAdapterPlanet(it.results)
            loadCompleted()
        }
    }

    private fun getData() {
        viewModel.getPlanets()
    }



}