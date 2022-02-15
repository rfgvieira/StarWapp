package com.example.activitystarwapp.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitystarwapp.R
import com.example.activitystarwapp.data.model.CharacterModel
import com.example.activitystarwapp.databinding.ActivityBuscapersonagemBinding
import com.example.activitystarwapp.presentation.adapter.CharacterAdapter
import com.example.activitystarwapp.presentation.viewmodel.TodosPersonagensViewModel

class BuscaPersonagemActivity : BuscaBaseActivity() {
    private lateinit var binding: ActivityBuscapersonagemBinding
    private lateinit var viewModel: TodosPersonagensViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscapersonagemBinding.inflate(layoutInflater)
        setContentView(binding.root)
       initializeView()

        viewModel = ViewModelProvider(this).get(TodosPersonagensViewModel :: class.java)
        viewModel.setUpList()
        initObserver()
        getData()
    }

    private fun initializeView() {
        setTitleActivity(R.string.personagens)
        setIconActivity(R.drawable.luke)
    }

    private fun initObserver() {
        viewModel.characterList.observe(this) {
            setUpAdapterCharacter(it.results)
        }
    }

    private fun getData() {
        viewModel.getCharacter()
    }

    private fun setUpAdapterCharacter(listCharacter: List<CharacterModel.Result>) {
        val adapter = CharacterAdapter(listCharacter, this)
        binding.rvPersonagens.adapter = adapter
        binding.rvPersonagens.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        loadCompleted()
    }

    override fun searchId() {
        viewModel.characterList.value?.let {
            if(id.isEmpty()){
                Toast.makeText(this,R.string.avisonulo,Toast.LENGTH_SHORT)
            } else if(id.toInt() > it.results.size ){
                Toast.makeText(this,R.string.avisoforarange,Toast.LENGTH_SHORT)
            }  else {
                setUpAdapterCharacter(listOf(it.results[id.toInt()-1]))
            }
        }

    }

}