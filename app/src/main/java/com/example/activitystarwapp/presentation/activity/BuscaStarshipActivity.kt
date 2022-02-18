package com.example.activitystarwapp.presentation.activity

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.activitystarwapp.R
import com.example.activitystarwapp.databinding.ActivityBuscaStarshipBinding
import com.example.activitystarwapp.presentation.viewmodel.TodosStarshipsViewModel

class BuscaStarshipActivity : BaseActivity() {
    private lateinit var binding : ActivityBuscaStarshipBinding
    private lateinit var viewModel: TodosStarshipsViewModel
    private lateinit var todosFragment: TodosFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscaStarshipBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initlizeViews()

        viewModel = ViewModelProvider(this).get(TodosStarshipsViewModel::class.java)
        viewModel.setUpList()

        todosFragment = TodosFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_buscastarships,todosFragment,"TodosStarship")
            .commit()

        initObservers()
    }

    override fun onResume() {
        super.onResume()
        todosFragment.clearRv()
        getData()
    }

    private fun initlizeViews() {
        setTitleActivity(R.string.espaconaves)
        setIconActivity(R.drawable.starship)
    }


    private fun getData() {
        loadStart()
        val timer = object : CountDownTimer(2500,1000){
            override fun onTick(p0: Long) { }
            override fun onFinish() {
                viewModel.getStarships()
            }
        }
        timer.start()

    }

    private fun initObservers() {
        viewModel.starshipList.observe(this) {
            todosFragment.setUpAdapter(it.results)
            loadCompleted()
        }
    }

    fun setUpItemFragment(position: Int) {
        viewModel.starshipList.value?.let {
            val fragmentItem = ItemFragment(position, it.results)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_buscastarships, fragmentItem, "ItemPlanetas")
                .commit()
        }
    }

    override fun searchId() {
        viewModel.starshipList.value?.let {
            if(id.isEmpty()){
                Toast.makeText(this,R.string.avisonulo, Toast.LENGTH_LONG)
            } else if(id.toInt() > it.results.size ){
                Toast.makeText(this,R.string.avisoforarange, Toast.LENGTH_LONG)
            }  else {
                todosFragment.setUpAdapter(listOf(it.results[id.toInt()-1]))
            }
        }
    }

}