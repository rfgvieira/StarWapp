package com.example.activitystarwapp.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitystarwapp.data.model.StarshipModel
import com.example.activitystarwapp.databinding.ActivityPlanetitemBinding
import com.example.activitystarwapp.databinding.ActivityTodosstarshipsBinding
import com.example.activitystarwapp.presentation.adapter.StarshipAdapter
import com.example.activitystarwapp.presentation.viewmodel.TodosPlanetasViewModel
import com.example.activitystarwapp.presentation.viewmodel.TodosStarshipsViewModel

class TodosStarshipsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTodosstarshipsBinding
    private lateinit var viewModel : TodosStarshipsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodosstarshipsBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(TodosStarshipsViewModel::class.java)
        setContentView(binding.root)

        initObservers()
        getData()
    }

    private fun getData() {
        binding.pbLoadingrv.visibility = View.VISIBLE
        binding.rvStarship.adapter = null
        viewModel.getStarships()
    }

    private fun initObservers() {
        viewModel.starshipList.observe(this) {
            updateStarships(it.results)
        }
    }

    fun updateStarships(listStarship: List<StarshipModel.Result>) {
        setUpAdapterStarship(listStarship)
        binding.pbLoadingrv.visibility = View.GONE
    }

    private fun setUpAdapterStarship(listStarship: List<StarshipModel.Result>) {
        val adapter = StarshipAdapter(listStarship)
        binding.rvStarship.adapter = adapter
        binding.rvStarship.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }


}