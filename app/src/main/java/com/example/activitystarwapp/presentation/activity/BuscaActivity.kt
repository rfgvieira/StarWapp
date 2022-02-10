package com.example.activitystarwapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.activitystarwapp.R
import com.example.activitystarwapp.data.model.CharacterModel
import com.example.activitystarwapp.databinding.ActivityBuscaunicoBinding
import com.example.activitystarwapp.presentation.viewmodel.BuscaViewModel

class BuscaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuscaunicoBinding
    private lateinit var viewModel: BuscaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscaunicoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(BuscaViewModel :: class.java)

        initObserver()
    }

    private fun initObserver() {
        viewModel.characterResult.observe(this) {
            bindValues(it)
        }
    }


    override fun onResume() {
        super.onResume()
        binding.btnIdpersonagem.setOnClickListener {
            val id : Int = binding.edtInputId.text.toString().toInt()
            viewModel.getData(id)
        }
    }

    private fun bindValues(model : CharacterModel.Result){
        with(model){
            binding.tvNomepersonagem.text = getString(R.string.nome) + name
            binding.tvAlturapersonagem.text = getString(R.string.aaaaa) + height
            binding.tvOlhopersonagem.text = getString(R.string.olho) + eye_Color
        }

    }
}