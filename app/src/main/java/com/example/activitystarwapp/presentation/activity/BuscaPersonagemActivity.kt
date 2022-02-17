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
    private lateinit var todosPersonagemFragment: TodosPersonagemFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscapersonagemBinding.inflate(layoutInflater)
        setContentView(binding.root)
       initializeView()

        viewModel = ViewModelProvider(this).get(TodosPersonagensViewModel :: class.java)
        viewModel.setUpList()

        todosPersonagemFragment = TodosPersonagemFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_buscapersonagens,todosPersonagemFragment,"TodosPersonagens")
            .commit()

        initObserver()
        getData()
    }

    override fun onResume() {
        super.onResume()
        todosPersonagemFragment.clearRv()
        getData()
    }

    private fun initializeView() {
        setTitleActivity(R.string.personagens)
        setIconActivity(R.drawable.luke)
    }

    private fun initObserver() {
        viewModel.characterList.observe(this) {
            todosPersonagemFragment.setUpAdapterCharacter(it.results)
            loadCompleted()
        }
    }

    private fun getData() {
        loadStart()
        viewModel.getCharacter()
    }

    fun setUpItemFragment(position: Int) {
        viewModel.characterList.value?.let {
            val fragmentItem = ItemPlanetFragment(position, it.results)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_buscapersonagens, fragmentItem, "ItemPlanetas")
                .commit()
        }
    }

    override fun searchId() {
        viewModel.characterList.value?.let {
            if(id.isEmpty()){
                Toast.makeText(this,R.string.avisonulo,Toast.LENGTH_SHORT)
            } else if(id.toInt() > it.results.size ){
                Toast.makeText(this,R.string.avisoforarange,Toast.LENGTH_SHORT)
            }  else {
                todosPersonagemFragment.setUpAdapterCharacter(listOf(it.results[id.toInt()-1]))
            }
        }

    }

}