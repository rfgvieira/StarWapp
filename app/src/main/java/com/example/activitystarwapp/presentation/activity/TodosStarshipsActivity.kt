package com.example.activitystarwapp.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitystarwapp.R
import com.example.activitystarwapp.data.model.StarshipModel
import com.example.activitystarwapp.databinding.ActivityPlanetitemBinding
import com.example.activitystarwapp.databinding.ActivityTodosstarshipsBinding
import com.example.activitystarwapp.presentation.adapter.StarshipAdapter
import com.example.activitystarwapp.presentation.viewmodel.TodosPlanetasViewModel
import com.example.activitystarwapp.presentation.viewmodel.TodosStarshipsViewModel

class TodosStarshipsActivity : BaseActivity() {

    private lateinit var binding : ActivityTodosstarshipsBinding
    private lateinit var viewModel : TodosStarshipsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTodosstarshipsBinding.inflate(layoutInflater)

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
        binding.tvResultcounttodosship.text =  getString(R.string.resultado) + listStarship.count()
        binding.rvStarship.adapter = adapter
        binding.rvStarship.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        loadCompleted()
    }


}