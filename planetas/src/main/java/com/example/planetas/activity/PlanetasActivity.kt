package com.example.planetas.activity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.base.BaseActivity
import com.example.base.BaseViewModel
import com.example.base.ItemFragment
import com.example.base.TodosFragment
import com.example.planetas.R
import com.example.planetas.databinding.ActivityPlanetasBinding
import com.example.planetas.viewmodel.PlanetasViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlanetasActivity : BaseActivity() {

    private lateinit var binding : ActivityPlanetasBinding
    private val viewModel: PlanetasViewModel by viewModel()
    private var todosFragment = TodosFragment()
    private var modo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        modo = intent.getIntExtra("modo",0)

        if(modo == 0)
            hideSearch()

        binding = ActivityPlanetasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeView()

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
        viewModel.planetList.observe(this) {
            todosFragment.setUpAdapter(it.results)
            loadCompleted()
        }
    }

    fun setUpTodosFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_planetas,todosFragment,"TodosPlanetas")
            .commit()
    }

    fun setUpItemFragment(position: Int) {
        viewModel.planetList.value?.let {
            val fragmentItem = ItemFragment(position,it.results)
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fl_planetas, fragmentItem, "ItemPlanetas")
                .commit()
        }
    }

    private fun getData() {
        loadStart()
        viewModel.getPlanets()
    }
    override fun searchId() {
        if(modo == 1){
            viewModel.planetList.value?.let {
                if(id.isEmpty()){
                    Toast.makeText(this, R.string.avisonulo, Toast.LENGTH_SHORT).show()
                } else if(id.toInt() > it.results.size ){
                    Toast.makeText(this, R.string.avisoforarange, Toast.LENGTH_SHORT).show()
                }  else {
                    todosFragment.setUpAdapter(listOf(it.results[id.toInt()-1]))
                }
            }
        }
    }
}