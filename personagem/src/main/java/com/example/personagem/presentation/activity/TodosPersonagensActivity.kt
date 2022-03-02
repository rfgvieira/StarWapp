package com.example.personagem.presentation.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.base.activity.BaseActivity
import com.example.personagem.R
import com.example.personagem.databinding.ActivityTodospersonagemBinding
import com.example.personagem.presentation.viewmodel.TodosPersonagensViewModel

class TodosPersonagensActivity : BaseActivity() {
    private lateinit var binding: ActivityTodospersonagemBinding
    private lateinit var viewModel: TodosPersonagensViewModel
    private lateinit var todosFragment: TodosFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTodospersonagemBinding.inflate(layoutInflater)
        hideSearch()
        setContentView(binding.root)
        initlizeViews()

        viewModel = ViewModelProvider(this).get(TodosPersonagensViewModel::class.java)

        todosFragment = TodosFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_todospersonagens,todosFragment,"TodosPersonagens")
            .commit()

        initObservers()
        getData()
    }



    private fun initlizeViews() {
        setTitleActivity(R.string.personagens)
        setIconActivity(R.drawable.luke)
    }
    override fun onResume() {
        super.onResume()
        todosFragment.clearRv()
        getData()
    }

    private fun initObservers() {
        viewModel.characterList.observe(this) {
            todosFragment.setUpAdapter(it.results)
            loadCompleted()
        }
    }

    fun setUpItemFragment(position: Int) {
        viewModel.characterList.value?.let {
            val fragmentItem = ItemFragment(position, it.results)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_todospersonagens, fragmentItem, "ItemPlanetas")
                .commit()
        }
    }

    private fun getData() {
        loadStart()
        viewModel.getCharacter()
    }

    override fun searchId() { }
}