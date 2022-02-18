package com.example.activitystarwapp.presentation.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitystarwapp.R
import com.example.activitystarwapp.data.model.PlanetsModel
import com.example.activitystarwapp.presentation.adapter.PlanetAdapter
import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.activitystarwapp.data.model.CharacterModel
import com.example.activitystarwapp.data.model.StarshipModel
import com.example.activitystarwapp.databinding.FragmentTodosBinding
import com.example.activitystarwapp.presentation.adapter.CharacterAdapter
import com.example.activitystarwapp.presentation.adapter.StarshipAdapter


class TodosFragment : Fragment() {

    private lateinit var binding : FragmentTodosBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentTodosBinding.inflate(inflater,container,false)
        return binding.root
    }

     fun setUpAdapter(list : List<*>,) {
         context?.let {
             var adapter : RecyclerView.Adapter<*>? = null
             when (list.firstOrNull()){
                 is PlanetsModel.Result -> adapter = PlanetAdapter(it, list as List<PlanetsModel.Result>)
                 is CharacterModel.Result -> adapter = CharacterAdapter(list as List<CharacterModel.Result>, it)
                 is StarshipModel.Result -> adapter = StarshipAdapter(list as List<StarshipModel.Result>, it)
                 null -> adapter = null
             }
             binding.tvResultcounttodosplanet.text = getString(R.string.resultado) + list.count()
             binding.rvPlanets.adapter = adapter
             binding.rvPlanets.layoutManager =
                LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.v("so pra logar", "então....")
    }

    fun clearRv(){
        binding.rvPlanets.adapter = null
    }
}