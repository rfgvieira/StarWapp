package com.example.activitystarwapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.activitystarwapp.databinding.ActivityPersonagemitemBinding
import kotlin.collections.ArrayList

class CharacterAdapter(private val characterList: ArrayList<CharacterModel.Result>, context : Context) :
    RecyclerView.Adapter<CharacterAdapter.CharacterHolder>() {
    private lateinit var binding: ActivityPersonagemitemBinding
    private val cont = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): CharacterHolder {
        binding = ActivityPersonagemitemBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return CharacterHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterAdapter.CharacterHolder, position: Int) {
        val characterItem = characterList[position]
        with(characterItem){
            binding.tvCharactername.text = name
            binding.tvCharacterheight.text = "Height: ${height}"
            binding.tvCharacterolho.text = "Eye Color: ${eye_Color}"

            setEyeColor(eye_Color)
        }
    }

    private fun setEyeColor(eyeColor: String){
        when(eyeColor){
            "red" -> binding.imvCharacterolho.setBackgroundColor(ContextCompat.getColor(cont,R.color.red))
            "yellow" -> binding.imvCharacterolho.setBackgroundColor(ContextCompat.getColor(cont,R.color.yellow))
            "blue" -> binding.imvCharacterolho.setBackgroundColor(ContextCompat.getColor(cont,R.color.blue))
            "brown" -> binding.imvCharacterolho.setBackgroundColor(ContextCompat.getColor(cont,R.color.brown))
            "blue-gray" -> binding.imvCharacterolho.setBackgroundColor(ContextCompat.getColor(cont,R.color.bluegray))
        }
    }

    override fun getItemCount(): Int = characterList.size

    class CharacterHolder(binding: ActivityPersonagemitemBinding) :
        RecyclerView.ViewHolder(binding.root){

    }
}