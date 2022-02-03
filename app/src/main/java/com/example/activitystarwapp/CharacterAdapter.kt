package com.example.activitystarwapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.activitystarwapp.databinding.ActivityPersonagemitemBinding
import kotlin.collections.ArrayList

class CharacterAdapter(private val characterList: ArrayList<CharacterModel.Result>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterHolder>() {
    private lateinit var binding: ActivityPersonagemitemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): CharacterHolder {
        binding = ActivityPersonagemitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CharacterHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterAdapter.CharacterHolder, position: Int) {
        with(holder){
            with(characterList[position]){
                binding.tvCharactername.text = name
                binding.tvCharacterheight.text = height
                binding.tvCharacterolho.text = eyeColor

                setEyeColor(eyeColor)
            }
        }
    }

    private fun setEyeColor(eyeColor: String){
        when(eyeColor){
            "red" -> binding.imvCharacterolho.setColorFilter(R.color.red)
            "yellow" -> binding.imvCharacterolho.setColorFilter(R.color.yellow)
            "blue" -> binding.imvCharacterolho.setColorFilter(R.color.blue)
            "brown" -> binding.imvCharacterolho.setColorFilter(R.color.brown)
            "blue-gray" -> binding.imvCharacterolho.setColorFilter(R.color.bluegray)
        }
    }

    override fun getItemCount(): Int = characterList.size

    class CharacterHolder(val binding: ActivityPersonagemitemBinding) : RecyclerView.ViewHolder(binding.root)
}