package com.example.activitystarwapp.presentation.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitystarwapp.R
import com.example.activitystarwapp.data.model.CharacterModel
import com.example.activitystarwapp.databinding.FragmentTodospersonagensBinding
import com.example.activitystarwapp.databinding.FragmentTodosplanetsBinding
import com.example.activitystarwapp.presentation.adapter.CharacterAdapter

class TodosPersonagemFragment : Fragment() {

    private lateinit var binding : FragmentTodospersonagensBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTodospersonagensBinding.inflate(inflater,container,false)
        return binding.root
    }



     fun setUpAdapterCharacter(listCharacter: List<CharacterModel.Result>) {
         context?.let {
             val adapter = CharacterAdapter(listCharacter, it)
             binding.tvResultcounttodospers.text = getString(R.string.resultado) + listCharacter.count()
             binding.rvPersonagens.adapter = adapter
             binding.rvPersonagens.layoutManager =
                 LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
         }
    }

    fun clearRv(){
        binding.rvPersonagens.adapter = null
    }
}