package com.example.espaconave.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.espaconave.data.model.StarshipModel
import com.example.espaconave.databinding.ActivityStarshipitemBinding
import com.example.espaconave.presentation.activity.BuscaStarshipActivity
import com.example.espaconave.presentation.activity.TodosStarshipsActivity


class StarshipAdapter (private val starshipList: List<StarshipModel.Result>, private val context: Context) : RecyclerView.Adapter<StarshipAdapter.StarshipHolder> (){

    private lateinit var binding : ActivityStarshipitemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarshipHolder {
        binding = ActivityStarshipitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StarshipHolder()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: StarshipHolder, position: Int) {
        with(starshipList[position]){

            binding.tvNomenave.text = name
            binding.tvTripulacaonave.text = "Tripulação ${crew}"
                if(passengers == "n/a")
                    binding.tvPassageirosnave.text = "Passageiros: " + 0
                else
                    binding.tvPassageirosnave.text = "Passageiros: " + passengers

            binding.clStarshipitem.setOnClickListener {
                if(context is TodosStarshipsActivity)
                    context.setUpItemFragment(position)
                else
                    (context as BuscaStarshipActivity).setUpItemFragment(position)
            }

        }
    }

    override fun getItemCount(): Int = starshipList.size

    inner class StarshipHolder () : RecyclerView.ViewHolder(binding.root){ }
}