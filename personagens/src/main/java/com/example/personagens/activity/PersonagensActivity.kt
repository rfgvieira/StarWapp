package com.example.personagens.activity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.base.BaseActivity
import com.example.base.ItemFragment
import com.example.base.TodosFragment
import com.example.personagens.R
import com.example.personagens.databinding.ActivityPersonagensBinding
import com.example.personagens.viewmodel.PersonagensViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonagensActivity : BaseActivity() {
    private lateinit var binding: ActivityPersonagensBinding
    private val viewModel: PersonagensViewModel by viewModel()
    private lateinit var todosFragment: TodosFragment
    private var modo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        modo = intent.getIntExtra("modo", 0)

        binding = ActivityPersonagensBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeView()

        if(modo == 0)
            hideSearch()

        viewModel.setUpList()

        setUpTodosFragment()
        initObserver()
    }

    private fun setUpTodosFragment() {
        todosFragment = TodosFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_personagens,todosFragment,"TodosPersonagens")
            .commit()
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
                .replace(R.id.fl_personagens, fragmentItem, "Item")
                .commit()
        }
    }


    override fun searchId() {
        if(modo == 1){
            viewModel.characterList.value?.let {
                if(id.isEmpty()){
                    Toast.makeText(this, R.string.avisonulo, Toast.LENGTH_SHORT)
                } else if(id.toInt() > it.results.size ){
                    Toast.makeText(this, R.string.avisoforarange, Toast.LENGTH_SHORT)
                }  else {
                    todosFragment.setUpAdapter(listOf(it.results[id.toInt()-1]))
                }
            }
        }


    }
}