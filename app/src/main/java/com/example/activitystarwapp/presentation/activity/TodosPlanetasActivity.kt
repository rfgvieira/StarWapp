package com.example.activitystarwapp.presentation.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.activitystarwapp.R
import com.example.activitystarwapp.databinding.ActivityTodosplanetasBinding
import com.example.activitystarwapp.presentation.viewmodel.TodosPlanetasViewModel

class TodosPlanetasActivity : BaseActivity() {
    private lateinit var binding : ActivityTodosplanetasBinding
    private lateinit var viewModel : TodosPlanetasViewModel
    private lateinit var todosPlanetsFragment: TodosPlanetsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTodosplanetasBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initlizeViews()

        viewModel = ViewModelProvider(this).get(TodosPlanetasViewModel::class.java)
        viewModel.setUpList()

        todosPlanetsFragment = TodosPlanetsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_todosplanetas,todosPlanetsFragment,"TodosPlanetas")
            .commit()

        initObservers()
        getData()
    }

    override fun onResume() {
        super.onResume()
        todosPlanetsFragment.clearRv()
        getData()
    }

    private fun initlizeViews() {
        setTitleActivity(R.string.planetas)
        setIconActivity(R.drawable.planet)
    }

    private fun initObservers() {
        viewModel.planetList.observe(this) {
            todosPlanetsFragment.setUpAdapterPlanet(it.results)
        }
    }

    fun setUpItemFragment(position: Int) {
        viewModel.planetList.value?.let {
            val fragmentItem = ItemPlanetFragment(position, it.results)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_todosplanetas, fragmentItem, "ItemPlanetas")
                .commit()
        }
    }

    private fun getData() {
        loadStart()
        viewModel.getPlanets()
    }
}