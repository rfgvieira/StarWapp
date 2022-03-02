package com.example.personagem.presentation.activity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.base.activity.BaseActivity
import com.example.personagem.R
import com.example.personagem.databinding.ActivityBuscapersonagemBinding
import com.example.personagem.presentation.viewmodel.TodosPersonagensViewModel

class BuscaPersonagemActivity : BaseActivity() {
    private lateinit var binding: ActivityBuscapersonagemBinding
    private lateinit var viewModel: TodosPersonagensViewModel
    private lateinit var todosFragment: TodosFragment
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityBuscapersonagemBinding.inflate(layoutInflater)
        setContentView(binding.root)
       initializeView()

        viewModel = ViewModelProvider(this).get(TodosPersonagensViewModel :: class.java)

        todosFragment = TodosFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_buscapersonagens,todosFragment,"TodosPersonagens")
            .commit()

        initObserver()
        getData()
    }

    override fun onResume() {
        super.onResume()
        todosFragment.clearRv()
        getData()
    }

    private fun initializeView() {
        setTitleActivity(R.string.personagens)
        setIconActivity(R.drawable.luke)
    }

    private fun initObserver() {
        viewModel.characterList.observe(this) {
            todosFragment.setUpAdapter(it.results)
            loadCompleted()
        }
    }

    private fun getData() {
        loadStart()
        viewModel.getCharacter()
    }

    fun setUpItemFragment(position: Int) {
        viewModel.characterList.value?.let {
            val fragmentItem = ItemFragment(position, it.results)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_buscapersonagens, fragmentItem, "Item")
                .commit()
        }
    }

    override fun volta() {
        supportFragmentManager.fragments
    }

    override fun searchId() {
        viewModel.characterList.value?.let {
            if(id.isEmpty()){
                Toast.makeText(this,R.string.avisonulo,Toast.LENGTH_SHORT)
            } else if(id.toInt() > it.results.size ){
                Toast.makeText(this,R.string.avisoforarange,Toast.LENGTH_SHORT)
            }  else {
                todosFragment.setUpAdapter(listOf(it.results[id.toInt()-1]))
            }
        }

    }

}