package com.example.activitystarwapp.presentation.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitystarwapp.data.model.StarshipModel
import com.example.activitystarwapp.databinding.ActivityTodosstarshipsBinding
import com.example.activitystarwapp.databinding.FragmentTodosstarshipsBinding
import com.example.activitystarwapp.presentation.adapter.StarshipAdapter

class TodosStarshipFragment : Fragment() {
    private lateinit var binding: FragmentTodosstarshipsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodosstarshipsBinding.inflate(inflater,container,false)
        return binding.root
    }

     fun setUpAdapterStarship(listStarship: List<StarshipModel.Result>) {
        context?.let {
            val adapter = StarshipAdapter(listStarship,it)
            binding.rvStarship.adapter = adapter
            binding.rvStarship.layoutManager =
                LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
            binding.tvResultcounttodosship.text = "Resultados: " + listStarship.size.toString()
        }
    }

    fun clearRv(){
        binding.rvStarship.adapter = null
    }
}