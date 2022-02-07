package com.example.activitystarwapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitystarwapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class TodosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TodosViewModel
    private var flag : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(TodosViewModel :: class.java)
        setContentView(binding.root)

        val tabLayout = binding.tlMain
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.person).setId(0))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.planet).setId(1))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.nave).setId(2))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                flag = tab?.id ?: -1
                when(tab?.id){
                    0 -> { binding.tvSubtitle.setText(R.string.personagens)
                        viewModel.characterList.value?.let {
                            if(it.isNotEmpty())
                                setUpAdapterCharacter(it)
                            else
                                getData()
                        }
                    }
                    1 -> { binding.tvSubtitle.setText(R.string.planetas)
                        if(viewModel.planetList.value!!.size > 0)
                            setUpAdapterPlanet()
                        else
                            getData()}
                    2 -> { binding.tvSubtitle.setText(R.string.espaconaves)
                        if(viewModel.starshipList.value!!.size > 0)
                            setUpAdapterStarship()
                        else
                            getData()}
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        initObservers()
    }

    private fun initObservers() {
        viewModel.characterList.observe(this, Observer {
            it.forEach { result ->
                Log.d("observa","observado : ${result.name}")
            }
            updateCharacter(it)
        })
    }

    override fun onResume() {
        super.onResume()
        flag = 0
        getData()
    }

    private fun getData(){
        binding.pbLoadingrv.visibility = View.VISIBLE
        binding.rvPersonagens.adapter = null
        when(flag) {
            0 -> viewModel.getCharacter()
            1 -> viewModel.getPlanets()
            2 -> viewModel.getStarships()
        }
    }

    fun updateCharacter(listCharacter: List<CharacterModel.Result>){
        setUpAdapterCharacter(listCharacter)
        binding.pbLoadingrv.visibility = View.GONE
    }

   fun updatePlanets(){
       setUpAdapterPlanet()
       binding.pbLoadingrv.visibility = View.GONE
   }

   fun updateStarships(){
       setUpAdapterStarship()
       binding.pbLoadingrv.visibility = View.GONE
   }

    private fun setUpAdapterCharacter(listCharacter : List<CharacterModel.Result>){
            val adapter = CharacterAdapter(listCharacter,this)
            binding.rvPersonagens.adapter = adapter
            binding.rvPersonagens.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    private fun setUpAdapterPlanet(){
        viewModel.planetList.value?.let {
            val adapter = PlanetAdapter(it)
            binding.rvPersonagens.adapter = adapter
            binding.rvPersonagens.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        }
    }

    private fun setUpAdapterStarship(){
        viewModel.starshipList.value?.let {
            val adapter = StarshipAdapter(it)
            binding.rvPersonagens.adapter = adapter
            binding.rvPersonagens.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        }
    }
}