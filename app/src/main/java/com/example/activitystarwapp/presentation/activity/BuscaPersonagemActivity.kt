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

class BuscaPersonagemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuscapersonagemBinding
    private lateinit var viewModel: TodosPersonagensViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscapersonagemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(TodosPersonagensViewModel :: class.java)

        binding.imvVoltaunicopers.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.imvBuscaper.setOnClickListener {
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
        viewModel.characterList.observe(this) {
            updateCharacter(it.results)
        }
    }

    private fun getData() {
        viewModel.getCharacter()
    }

    fun updateCharacter(listCharacter: List<CharacterModel.Result>) {
        binding.pbLoadingrv.visibility = View.GONE
        setUpAdapterCharacter(listCharacter)

    }

    private fun setUpAdapterCharacter(listCharacter: List<CharacterModel.Result>) {
        val adapter = CharacterAdapter(listCharacter, this)
        binding.tvResultcounttodospers.text = getString(R.string.resultado) + listCharacter.count()
        binding.rvPersonagens.adapter = adapter
        binding.rvPersonagens.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    private fun searchId() {
        viewModel.characterList.value?.let {
            val id = binding.edtSearchbarbuscaunicoper.text.toString()
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