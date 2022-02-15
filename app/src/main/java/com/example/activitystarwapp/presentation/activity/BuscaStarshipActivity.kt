package com.example.activitystarwapp.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitystarwapp.R
import com.example.activitystarwapp.data.model.StarshipModel
import com.example.activitystarwapp.databinding.ActivityBuscaStarshipBinding
import com.example.activitystarwapp.presentation.adapter.StarshipAdapter
import com.example.activitystarwapp.presentation.viewmodel.TodosStarshipsViewModel

class BuscaStarshipActivity : BuscaBaseActivity() {
    private lateinit var binding : ActivityBuscaStarshipBinding
    private lateinit var viewModel: TodosStarshipsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscaStarshipBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initlizeViews()

        viewModel = ViewModelProvider(this).get(TodosStarshipsViewModel::class.java)
        viewModel.setUpList()

        initObservers()
        getData()
    }

    private fun initlizeViews() {
        setTitleActivity(R.string.espaconaves)
        setIconActivity(R.drawable.starship)
    }


    private fun getData() {
        viewModel.getStarships()
    }

    private fun initObservers() {
        viewModel.starshipList.observe(this) {
            setUpAdapterStarship(it.results)
        }
    }

    private fun setUpAdapterStarship(listStarship: List<StarshipModel.Result>) {
        val adapter = StarshipAdapter(listStarship)
        binding.rvShip.adapter = adapter
        binding.rvShip.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        loadCompleted()
    }

    override fun searchId() {
        viewModel.starshipList.value?.let {
            if(id.isEmpty()){
                Toast.makeText(this,R.string.avisonulo, Toast.LENGTH_LONG)
            } else if(id.toInt() > it.results.size ){
                Toast.makeText(this,R.string.avisoforarange, Toast.LENGTH_LONG)
            }  else {
                setUpAdapterStarship(listOf(it.results[id.toInt()-1]))
            }
        }
    }

}