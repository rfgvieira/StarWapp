package com.example.activitystarwapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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

            binding.tvNomeplanet.text =name
            binding.tvDiametroplanet.text = "Diametro: ${diameter} KM"
            if(population != "unknown") {
                val pop = population.substring(0,population.length - 3)
                if(pop.length > 3){
                    binding.tvPopulationplanet.text =
                        "População: ${pop.substring(0,pop.length - 3)}M"
                } else {
                    binding.tvPopulationplanet.text =
                        "População: ${pop}K"
                }

            }
            else
                binding.tvPopulationplanet.text = "População: ${population}"

        }
    }

    override fun getItemCount(): Int = planetList.size

    inner class PlanetHolder() : RecyclerView.ViewHolder(binding.root) {

    }
}