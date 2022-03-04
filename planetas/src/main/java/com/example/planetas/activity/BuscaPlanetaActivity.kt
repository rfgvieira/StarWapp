package com.example.planetas.activity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.planetas.viewmodel.TodosPlanetasViewModel
import com.example.base.BaseActivity
import com.example.base.ItemFragment
import com.example.base.TodosFragment
import com.example.planetas.R
import com.example.planetas.databinding.ActivityBuscaplanetaBinding

class BuscaPlanetaActivity : BaseActivity() {
    private lateinit var binding : ActivityBuscaplanetaBinding
    private lateinit var viewModel: TodosPlanetasViewModel
    private var todosFragment = TodosFragment()
    private var flag = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBuscaplanetaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeView()

        viewModel = ViewModelProvider(this).get(TodosPlanetasViewModel::class.java)
        viewModel.setUpList()

        setUpTodosFragment()

        initObserver()
    }

    override fun onResume() {
        super.onResume()
        todosFragment.clearRv()
        getData()
    }

    private fun initializeView() {
        setTitleActivity(R.string.planetas)
        setIconActivity(R.drawable.planet)
    }

    private fun initObserver() {
        viewModel.planetList.observe(this, {
            todosFragment.setUpAdapter(it.results)
            loadCompleted()
        })
    }

    fun setUpTodosFragment(){
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fl_buscaplanets,todosFragment,"TodosPlanetas")
            .commit()
        flag = 0
    }

    fun setUpItemFragment(position: Int) {
        flag = 1
        viewModel.planetList.value?.let {
            val fragmentItem = ItemFragment(position,it.results)
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fl_buscaplanets, fragmentItem, "ItemPlanetas")
                .commit()
        }
    }

    private fun getData() {
        loadStart()
        viewModel.getPlanets()
    }
    override fun searchId() {
        viewModel.planetList.value?.let {
            if(id.isEmpty()){
                Toast.makeText(this,R.string.avisonulo, Toast.LENGTH_SHORT).show()
            } else if(id.toInt() > it.results.size ){
                Toast.makeText(this,R.string.avisoforarange, Toast.LENGTH_SHORT).show()
            }  else {
                todosFragment.setUpAdapter(listOf(it.results[id.toInt()-1]))
            }
        }
    }

    override fun volta() {
        if(flag == 1){
            supportFragmentManager.popBackStack()
        } else if (flag == 0)
            super.volta()
    }

}