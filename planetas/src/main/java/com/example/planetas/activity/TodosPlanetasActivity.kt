package com.example.planetas.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.planetas.viewmodel.TodosPlanetasViewModel
import com.example.base.BaseActivity
import com.example.base.ItemFragment
import com.example.base.TodosFragment
import com.example.planetas.R
import com.example.planetas.databinding.ActivityTodosplanetasBinding

class TodosPlanetasActivity : BaseActivity() {
    private lateinit var binding : ActivityTodosplanetasBinding
    private lateinit var viewModel : TodosPlanetasViewModel
    private lateinit var todosFragment: TodosFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideSearch()
        binding = ActivityTodosplanetasBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initlizeViews()

        viewModel = ViewModelProvider(this).get(TodosPlanetasViewModel::class.java)
        viewModel.setUpList()

        todosFragment = TodosFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_todosplanetas,todosFragment,"TodosPlanetas")
            .commit()

        initObservers()
        getData()
    }

    override fun searchId() {
    }

    override fun onResume() {
        super.onResume()
        todosFragment.clearRv()
        getData()
    }

    private fun initlizeViews() {
        setTitleActivity(R.string.planetas)
        setIconActivity(R.drawable.planet)
    }

    private fun initObservers() {
        viewModel.planetList.observe(this) {
            todosFragment.setUpAdapter(it.results)
            loadCompleted()
        }
    }

    fun setUpItemFragment(position: Int) {
        viewModel.planetList.value?.let {
            val fragmentItem = ItemFragment(position, it.results)
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