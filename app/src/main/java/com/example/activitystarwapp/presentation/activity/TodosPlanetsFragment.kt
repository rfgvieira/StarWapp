package com.example.activitystarwapp.presentation.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitystarwapp.R
import com.example.activitystarwapp.data.model.PlanetsModel
import com.example.activitystarwapp.databinding.FragmentTodosplanetsBinding
import com.example.activitystarwapp.presentation.adapter.PlanetAdapter
import android.app.Activity
import android.content.Context


class TodosPlanetsFragment : Fragment() {

    private lateinit var binding : FragmentTodosplanetsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentTodosplanetsBinding.inflate(inflater,container,false)
        return binding.root
    }

     fun setUpAdapterPlanet(listPlanet: List<PlanetsModel.Result>,) {
         context?.let {
             val adapter = PlanetAdapter(it, listPlanet)
             binding.tvResultcounttodosplanet.text = getString(R.string.resultado) + listPlanet.count()
             binding.rvPlanets.adapter = adapter
             binding.rvPlanets.layoutManager =
                LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
        }
    }

    fun clearRv(){
        binding.rvPlanets.adapter = null
    }
}