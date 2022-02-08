package com.example.activitystarwapp.presentation.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.activitystarwapp.R
import com.example.activitystarwapp.data.model.StarshipModel
import com.example.activitystarwapp.databinding.ActivityStarshipitemBinding

class StarshipAdapter (private val starshipList: List<StarshipModel.Result>) : RecyclerView.Adapter<StarshipAdapter.StarshipHolder> (){

    private lateinit var binding : ActivityStarshipitemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarshipHolder {
        binding = ActivityStarshipitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StarshipHolder()
    }

    override fun onBindViewHolder(holder: StarshipHolder, position: Int) {
        with(starshipList[position]){
            with(Resources.getSystem()){
                binding.tvNomenave.text = name
                binding.tvTripulacaonave.text = getString(R.string.tripulacao) + crew
                    if(passengers == "n/a")
                        binding.tvPassageirosnave.text = getString(R.string.passageiro) + passengers
                    else
                        binding.tvPassageirosnave.text = getString(R.string.passageiro) + passengers
            }

        }
    }

    override fun getItemCount(): Int = starshipList.size

    inner class StarshipHolder () : RecyclerView.ViewHolder(binding.root){ }
}