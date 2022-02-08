package com.example.activitystarwapp.presentation.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.activitystarwapp.R
import com.example.activitystarwapp.data.model.PlanetsModel
import com.example.activitystarwapp.databinding.ActivityPlanetitemBinding

class PlanetAdapter (private  val planetList: List<PlanetsModel.Result>) :
    RecyclerView.Adapter<PlanetAdapter.PlanetHolder>() {
    private  lateinit var binding: ActivityPlanetitemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetHolder {
        binding = ActivityPlanetitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PlanetHolder()
    }

    override fun onBindViewHolder(holder: PlanetHolder, position: Int) {
        with(planetList[position]){
            with(Resources.getSystem()){
                binding.tvNomeplanet.text =name
                binding.tvDiametroplanet.text = getString(R.string.diametro) +
                        diameter + getString(R.string.km)
                        if(population != "unknown") {
                            val pop = population.substring(0,population.length - 3)
                            if(pop.length > 3){
                                binding.tvPopulationplanet.text =
                                    getString(R.string.populacao) +
                                            pop.substring(0,pop.length - 3) +
                                            getString(R.string.milhao)
                            } else {
                                binding.tvPopulationplanet.text =
                                    getString(R.string.populacao) + pop + getString(R.string.mil)
                            }

                        }
                        else
                            binding.tvPopulationplanet.text = getString(R.string.populacao) + population
            }
        }
    }

    override fun getItemCount(): Int = planetList.size

    inner class PlanetHolder() : RecyclerView.ViewHolder(binding.root) {

    }
}