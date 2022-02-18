package com.example.activitystarwapp.presentation.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.activitystarwapp.data.model.CharacterModel
import com.example.activitystarwapp.data.model.PlanetsModel
import com.example.activitystarwapp.data.model.StarshipModel
import com.example.activitystarwapp.databinding.FragmentDetalheBinding

class ItemFragment(private val position: Int, private val results: List<*>) : Fragment() {
    private lateinit var binding : FragmentDetalheBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalheBinding.inflate(inflater,container,false)

        when(results.firstOrNull()){
            is PlanetsModel.Result -> bindValuesPlanet()
            is CharacterModel.Result -> bindValuesCharacter()
            is StarshipModel.Result -> bindValuesStarship()
        }

        return binding.root
    }

    private fun bindValuesStarship() {
        var item : StarshipModel.Result

        if(position == -1)
            item =  results.get(0) as StarshipModel.Result
        else
            item = (results as List<StarshipModel.Result>)[position]

        with(item){
            binding.tvNomedetalhe.text = name
            binding.attr1detalhe.text = "Comprimento: " + length
            binding.attr2detalhe.text = "Modelo: " + model
            binding.attr3detalhe.text = "Tripulação: " + crew
            binding.attr4detalhe.text = "Passageiros: " + passengers
            binding.attr5detalhe.text = "Custo: " + costInCredits
            binding.attr6detalhe.text = "Capacidade: " + cargoCapacity
        }
    }

    private fun bindValuesCharacter() {
        var item : CharacterModel.Result
        if(position == -1)
            item =  results.get(0) as CharacterModel.Result
        else
            item =  (results as List<CharacterModel.Result>)[position]
        with(item){
            binding.tvNomedetalhe.text = name
            binding.attr1detalhe.text = "Altura: " + height
                    binding.attr2detalhe.text = "Peso: " + mass
            binding.attr3detalhe.text = "Cor do cabelo: " + hair_Color
            binding.attr4detalhe.text = "Cor da pele: " + skin_Color
            binding.attr5detalhe.text = "Cor do olho: " + eye_Color
            binding.attr6detalhe.text = "Genero: " + gender
        }
    }

    private fun bindValuesPlanet() {
        var item  :  PlanetsModel.Result
        if(position == -1)
            item = results.get(0) as PlanetsModel.Result
        else
            item = (results as List<PlanetsModel.Result>)[position]
        with(item){
            binding.tvNomedetalhe.text = name
            binding.attr1detalhe.text = "Diametro: " + diameter
            binding.attr2detalhe.text = "Tempo de rotação: " + rotationPeriod
            binding.attr3detalhe.text = "Tempo de orbital: " + orbitalPeriod
            binding.attr4detalhe.text = "Gravidade: " + gravity.split(" ")[0]
            binding.attr5detalhe.text = "População: " + population
            binding.attr6detalhe.text = "Clima: " + climate
        }

    }
}