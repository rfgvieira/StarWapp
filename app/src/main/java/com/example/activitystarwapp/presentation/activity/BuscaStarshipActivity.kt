package com.example.activitystarwapp.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitystarwapp.R
import com.example.activitystarwapp.data.model.StarshipModel
import com.example.activitystarwapp.databinding.ActivityBuscaStarshipBinding
import com.example.activitystarwapp.presentation.adapter.StarshipAdapter
import com.example.activitystarwapp.presentation.viewmodel.TodosStarshipsViewModel
import kotlinx.coroutines.delay

class BuscaStarshipActivity : BuscaBaseActivity() {
    private lateinit var binding : ActivityBuscaStarshipBinding
    private lateinit var viewModel: TodosStarshipsViewModel
    private lateinit var starshipFragment: TodosStarshipFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscaStarshipBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initlizeViews()

        viewModel = ViewModelProvider(this).get(TodosStarshipsViewModel::class.java)
        viewModel.setUpList()

        starshipFragment = TodosStarshipFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_buscastarships,starshipFragment,"TodosStarship")
            .commit()

        initObservers()
    }

    override fun onResume() {
        super.onResume()
        starshipFragment.clearRv()
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
            starshipFragment.setUpAdapterStarship(it.results)
            loadCompleted()
        }
    }

    fun setUpItemFragment(position: Int) {
        viewModel.starshipList.value?.let {
            val fragmentItem = ItemPlanetFragment(position, it.results)
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
                starshipFragment.setUpAdapterStarship(listOf(it.results[id.toInt()-1]))
            }
        }
    }

}