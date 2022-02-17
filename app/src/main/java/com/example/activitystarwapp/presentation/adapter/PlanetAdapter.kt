package com.example.activitystarwapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.activitystarwapp.data.model.PlanetsModel
import com.example.activitystarwapp.databinding.ActivityPlanetitemBinding
import com.example.activitystarwapp.presentation.activity.BuscaPlanetaActivity
import com.example.activitystarwapp.presentation.activity.TodosPlanetasActivity

class PlanetAdapter (private val context : Context, private  val planetList: List<PlanetsModel.Result>) :
    RecyclerView.Adapter<PlanetAdapter.PlanetHolder>() {
    private  lateinit var binding: ActivityPlanetitemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetHolder {
        binding = ActivityPlanetitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PlanetHolder()
    }

    override fun onBindViewHolder(holder: PlanetHolder, position: Int) {
        with(planetList[position]){

                binding.tvNomeplanet.text =name
                binding.tvDiametroplanet.text = "Diametro: ${diameter} km"
                        if(population != "unknown") {
                            val pop = population.substring(0,population.length - 3)
                            if(pop.length > 3){
                                binding.tvPopulationplanet.text = "População: ${ pop.substring(0,pop.length - 3)} M"
                            } else {
                                binding.tvPopulationplanet.text = "População: ${population} K"
                            }

                        }
                        else
                            binding.tvPopulationplanet.text = "População: ${population}"

                binding.clPlanetitem.setOnClickListener {
                    if(context is TodosPlanetasActivity)
                        context.setUpItemFragment(position)
                    else
                        (context as BuscaPlanetaActivity).setUpItemFragment(position)
                }
        }
    }

    override fun getItemCount(): Int = planetList.size

    inner class PlanetHolder() : RecyclerView.ViewHolder(binding.root) {

    }
}