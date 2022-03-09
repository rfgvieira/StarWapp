package com.example.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.base.databinding.ItemBaseBinding
import com.example.model.CharacterModel
import com.example.model.PlanetsModel
import com.example.model.StarshipModel

class BaseAdapter internal constructor(
    private val context: Context,
    private val listBase: List<*>,
    private val viewModel: BaseViewModel
) : RecyclerView.Adapter<BaseAdapter.BaseHolder>() {
    private lateinit var binding : ItemBaseBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        binding = ItemBaseBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BaseHolder()
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int){
        when(listBase[position]){
            is PlanetsModel.Result -> bindValuesPlanet(position)
            is CharacterModel.Result -> bindValuesCharacter(position)
            is StarshipModel.Result -> bindValuesStarship(position)
        }
    }

    private fun bindValuesStarship(position: Int) {
        with(listBase[position] as StarshipModel.Result){
            with(binding){
                imvAdaptericon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.starship))
                tvNomeadapter.text = name
                tvAttr1adapter.text =  "Tripulação ${crew}"
                if(passengers == "n/a")
                    tvAttr2adapter.text = "Passageiros: " + 0
                else
                    tvAttr2adapter.text = "Passageiros: " + passengers

                clBaseAdapter.setOnClickListener {
                    viewModel.adapterSwitch(position)
                }
            }
        }
    }

    private fun bindValuesCharacter(position: Int) {
        with(listBase[position] as CharacterModel.Result){
            with(binding){
                imvAdaptericon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.luke))
                tvNomeadapter.text = name
                tvAttr1adapter.text ="Altura: "+ height
                tvAttr2adapter.text = "Cor dos Olhos: " + eye_Color

                clBaseAdapter.setOnClickListener {
                    viewModel.adapterSwitch(position)
                }
            }
        }
    }

    private fun bindValuesPlanet(position: Int) {
        with(listBase[position] as PlanetsModel.Result){
            with(binding){
                imvAdaptericon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.planet))
                tvNomeadapter.text =name
                tvAttr1adapter.text = "Diametro: ${diameter} km"
                if(population != "unknown") {
                    val pop = population.substring(0,population.length - 3)
                    if(pop.length > 3){
                        tvAttr2adapter.text = "População: ${ pop.substring(0,pop.length - 3)} M"
                    } else {
                        tvAttr2adapter.text = "População: ${population} K"
                    }

                }
                else
                    tvAttr2adapter.text = "População: ${population}"

                clBaseAdapter.setOnClickListener {
                    viewModel.adapterSwitch(position)
                }
            }
        }
    }

    override fun getItemCount(): Int = listBase.size

    inner class BaseHolder() : RecyclerView.ViewHolder(binding.root){}
}