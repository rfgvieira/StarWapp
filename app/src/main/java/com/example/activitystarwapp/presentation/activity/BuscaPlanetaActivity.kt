package com.example.activitystarwapp.presentation.activity

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.activitystarwapp.R
import com.example.activitystarwapp.data.model.PlanetsModel
import com.example.activitystarwapp.databinding.ActivityBuscaplanetaBinding
import com.example.activitystarwapp.presentation.adapter.PlanetAdapter
import com.example.activitystarwapp.presentation.viewmodel.TodosPlanetasViewModel

class BuscaPlanetaActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBuscaplanetaBinding
    private lateinit var viewModel: TodosPlanetasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscaplanetaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(TodosPlanetasViewModel::class.java)

        binding.imvVoltaunicoplanet.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.imvBuscaplanet.setOnClickListener {
            searchId()
        }

        viewModel.setUpList()

        initObserver()
        getData()

    }

    override fun onResume() {
        super.onResume()
        binding.pbLoadingrv.visibility = View.VISIBLE
    }

    private fun initObserver() {
        viewModel.planetList.observe(this, {
            updatePlanet(it.results)
        })
    }

    private fun getData() {
        viewModel.getPlanets()
    }

    private fun updatePlanet(planetList : List<PlanetsModel.Result>) {
        setUpAdapter(planetList)
    }

    private fun setUpAdapter(planetList : List<PlanetsModel.Result>){
        val adapter = PlanetAdapter(planetList)
        binding.rvPlanet.adapter = adapter
        binding.rvPlanet.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.pbLoadingrv.visibility = View.GONE
    }

    private fun searchId() {
        viewModel.planetList.value?.let {
            val id = binding.edtSearchbarbuscaunicoplanet.text.toString()
            if(id.isEmpty()){
                Toast.makeText(this,R.string.avisonulo, Toast.LENGTH_SHORT).show()
            } else if(id.toInt() > it.results.size ){
                Toast.makeText(this,R.string.avisoforarange, Toast.LENGTH_SHORT).show()
            }  else {
                setUpAdapter(listOf(it.results[id.toInt()-1]))
            }
        }
    }

}