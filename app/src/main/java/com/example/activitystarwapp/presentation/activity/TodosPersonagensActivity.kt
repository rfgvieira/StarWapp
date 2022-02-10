package com.example.activitystarwapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitystarwapp.R
import com.example.activitystarwapp.data.model.CharacterModel
import com.example.activitystarwapp.data.model.PlanetsModel
import com.example.activitystarwapp.data.model.StarshipModel
import com.example.activitystarwapp.databinding.ActivityTodospersonagemBinding
import com.example.activitystarwapp.presentation.adapter.CharacterAdapter
import com.example.activitystarwapp.presentation.adapter.PlanetAdapter
import com.example.activitystarwapp.presentation.adapter.StarshipAdapter
import com.example.activitystarwapp.presentation.viewmodel.TodosPersonagensViewModel

class TodosPersonagensActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodospersonagemBinding
    private lateinit var viewModel: TodosPersonagensViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodospersonagemBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(TodosPersonagensViewModel::class.java)
        binding.pbLoadingrv.visibility = View.VISIBLE
        setContentView(binding.root)
        initObservers()
        getData()
    }

    private fun initObservers() {
        viewModel.characterList.observe(this) {
            updateCharacter(it.results)
        }
    }

    private fun getData() {
        binding.pbLoadingrv.visibility = View.VISIBLE
        viewModel.getCharacter()
    }

    fun updateCharacter(listCharacter: List<CharacterModel.Result>) {
        setUpAdapterCharacter(listCharacter)
        binding.pbLoadingrv.visibility = View.GONE
    }

    private fun setUpAdapterCharacter(listCharacter: List<CharacterModel.Result>) {
        val adapter = CharacterAdapter(listCharacter, this)
        binding.tvResultcounttodospers.text = getString(R.string.resultado) + listCharacter.count()
        binding.rvPersonagens.adapter = adapter
        binding.rvPersonagens.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }




}