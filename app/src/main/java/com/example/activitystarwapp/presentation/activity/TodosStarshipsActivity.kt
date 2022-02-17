package com.example.activitystarwapp.presentation.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.activitystarwapp.R
import com.example.activitystarwapp.databinding.ActivityTodosstarshipsBinding
import com.example.activitystarwapp.presentation.viewmodel.TodosStarshipsViewModel

class TodosStarshipsActivity : BaseActivity() {
    private lateinit var binding : ActivityTodosstarshipsBinding
    private lateinit var viewModel : TodosStarshipsViewModel
    private lateinit var starshipFragment: TodosStarshipFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTodosstarshipsBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initlizeViews()

        viewModel = ViewModelProvider(this).get(TodosStarshipsViewModel::class.java)
        viewModel.setUpList()

        starshipFragment = TodosStarshipFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_todosstarships,starshipFragment,"TodosStarship")
            .commit()

        initObservers()
        getData()
    }

    override fun onResume() {
        super.onResume()
        starshipFragment.clearRv()
        getData()
    }

    private fun initlizeViews() {
        setTitleActivity(R.string.espaconaves)
        setIconActivity(R.drawable.starship)
    }

    private fun initObservers() {
        viewModel.starshipList.observe(this) {
            starshipFragment.setUpAdapterStarship(it.results)
            loadCompleted()
        }
    }

    fun setUpItemFragment(position: Int) {
        viewModel.starshipList.value?.let {
            val fragmentItem = ItemPlanetFragment(position, it.results)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_todosstarships, fragmentItem, "ItemPlanetas")
                .commit()
        }
    }

    private fun getData() {
        loadStart()
        viewModel.getStarships()
    }
}