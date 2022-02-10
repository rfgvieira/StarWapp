package com.example.activitystarwapp.presentation.adapter

import android.os.Bundle
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.activitystarwapp.data.model.CharacterModel
import com.example.activitystarwapp.R
import com.example.activitystarwapp.databinding.ActivityPersonagemitemBinding

class CharacterAdapter(private val characterList: List<CharacterModel.Result>, context: Context) :
    RecyclerView.Adapter<CharacterAdapter.CharacterHolder>() {
    private lateinit var binding: ActivityPersonagemitemBinding
    private val cont = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): CharacterHolder {
        binding = ActivityPersonagemitemBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return CharacterHolder()
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val characterItem = characterList[position]
        with(characterItem){
            with(Resources.getSystem()){
                binding.tvCharactername.text = name
                binding.tvCharacterheight.text ="Altura: "+ height
                binding.tvCharacterolho.text = "Cor dos Olhos: " + eye_Color
            }


//            setEyeColor(eye_Color)
        }
    }

//    private fun setEyeColor(eyeColor: String){
//        when(eyeColor){
//            "red" -> binding.imvCharacterolho.setBackgroundColor(ContextCompat.getColor(cont,
//                R.color.red
//            ))
//            "yellow" -> binding.imvCharacterolho.setBackgroundColor(ContextCompat.getColor(cont,
//                R.color.yellow
//            ))
//            "blue" -> binding.imvCharacterolho.setBackgroundColor(ContextCompat.getColor(cont,
//                R.color.blue
//            ))
//            "brown" -> binding.imvCharacterolho.setBackgroundColor(ContextCompat.getColor(cont,
//                R.color.brown
//            ))
//            "blue-gray" -> binding.imvCharacterolho.setBackgroundColor(ContextCompat.getColor(cont,
//                R.color.bluegray
//            ))
//        }
//    }

    override fun getItemCount(): Int = characterList.size

    inner class CharacterHolder() : RecyclerView.ViewHolder(binding.root){

    }
}