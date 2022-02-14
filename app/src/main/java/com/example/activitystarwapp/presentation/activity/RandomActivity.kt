package com.example.activitystarwapp.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.activitystarwapp.R
import com.example.activitystarwapp.data.model.CharacterModel
import com.example.activitystarwapp.databinding.ActivityRandomBinding
import com.example.activitystarwapp.presentation.viewmodel.RandomViewModel

class RandomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRandomBinding
    private lateinit var viewModel: RandomViewModel
    private val rand = (0..2).random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(RandomViewModel::class.java)

        binding.cvRandomitem.visibility = View.GONE
        binding.imvVoltarandom.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        setUpActivity(rand)
    }

    private fun setUpActivity(rand: Int) {
        when(rand){
            0 -> {
                initObserveCharacter()
                viewModel.getCharacter() }
            1 -> {
                initObservePlanet()
                viewModel.getPlanet() }
            2 -> {
                initObserveStarship()
                viewModel.getStarship() }
        }

    }

    private fun initObserveCharacter() {
        viewModel.characterList.observe(this, {
            bindValues(it.name, it.height, it.eye_Color)
        })
    }

    private fun initObservePlanet() {
        viewModel.planetList.observe(this, {
            bindValues(it.name, it.diameter, it.population)
        })
    }

    private fun initObserveStarship() {
        viewModel.starshipList.observe(this, {
            bindValues(it.name, it.crew, it.passengers)
        })
    }

    private fun bindValues(name: String, attr1 : String, attr2 : String) {

        binding.pbLoadingrv.visibility = View.GONE
        binding.cvRandomitem.visibility = View.VISIBLE
        var attr1Name = ""
        var attr2Name = ""
        var attr1Extra = ""

        when(rand){
            0 -> {
                binding.tvTitlerandom.text = getString(R.string.personagens)
                binding.iconrandom.setImageResource(R.drawable.luke)
                binding.imvRandicon.setImageResource(R.drawable.luke)
                attr1Name = "Altura: "
                attr1Extra = " m"
                attr2Name = "Cor dos Olhos: "}
            1 -> {
                binding.tvTitlerandom.text = getString(R.string.planetas)
                binding.iconrandom.setImageResource(R.drawable.planet)
                binding.imvRandicon.setImageResource(R.drawable.planet)
                attr1Name = "Diametro: "
                attr1Extra = " km"
                attr2Name = "População: "}
            2 -> {
                binding.tvTitlerandom.text = getString(R.string.espaconaves)
                binding.iconrandom.setImageResource(R.drawable.starship)
                binding.imvRandicon.setImageResource(R.drawable.starship)
                attr1Name = "Tripulação: "
                attr2Name = "Passageiros: "}
        }

        binding.tvRandname.text = name
        if (rand == 0 )
            binding.tvRandatr1.text = attr1Name + (attr1.toDouble()/100).toString() + attr1Extra
        else
            binding.tvRandatr1.text = attr1Name + attr1+ attr1Extra
        binding.tvRandatr2.text = attr2Name + attr2

    }
}