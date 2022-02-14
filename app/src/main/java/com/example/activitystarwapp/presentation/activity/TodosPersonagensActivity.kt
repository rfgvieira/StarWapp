package com.example.activitystarwapp.presentation.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitystarwapp.R
import com.example.activitystarwapp.data.model.CharacterModel
import com.example.activitystarwapp.databinding.ActivityTodospersonagemBinding
import com.example.activitystarwapp.presentation.adapter.CharacterAdapter
import com.example.activitystarwapp.presentation.viewmodel.TodosPersonagensViewModel

class TodosPersonagensActivity : BaseActivity() {
    private lateinit var binding: ActivityTodospersonagemBinding
    private lateinit var viewModel: TodosPersonagensViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTodospersonagemBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initlizeViews()

        viewModel = ViewModelProvider(this).get(TodosPersonagensViewModel::class.java)
        viewModel.setUpList()

        initObservers()
        getData()
    }

    private fun initlizeViews() {
        setTitleActivity(R.string.personagens)
        setIconActivity(R.drawable.luke)
    }

    private fun initObservers() {
        viewModel.characterList.observe(this) {
            loadCompleted()
            setUpAdapterCharacter(it.results)
        }
    }

    private fun getData() {
        viewModel.getCharacter()
    }

    private fun setUpAdapterCharacter(listCharacter: List<CharacterModel.Result>) {
        val adapter = CharacterAdapter(listCharacter, this)
        binding.tvResultcounttodospers.text = getString(R.string.resultado) + listCharacter.count()
        binding.rvPersonagens.adapter = adapter
        binding.rvPersonagens.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }




}