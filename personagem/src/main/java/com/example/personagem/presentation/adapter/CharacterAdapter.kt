package com.example.personagem.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personagem.data.model.CharacterModel
import com.example.personagem.databinding.ActivityPersonagemitemBinding
import com.example.personagem.presentation.activity.BuscaPersonagemActivity
import com.example.personagem.presentation.activity.TodosPersonagensActivity

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

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val characterItem = characterList[position]
        with(characterItem){

                binding.tvCharactername.text = name
                binding.tvCharacterheight.text ="Altura: "+ height
                binding.tvCharacterolho.text = "Cor dos Olhos: " + eye_Color
            }
            binding.clPersonagemitem.setOnClickListener {
                if(cont is TodosPersonagensActivity)
                    cont.setUpItemFragment(position)
                else
                    (cont as BuscaPersonagemActivity).setUpItemFragment(position)

            }


//            setEyeColor(eye_Color)

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