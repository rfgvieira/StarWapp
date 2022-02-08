package com.example.activitystarwapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.activitystarwapp.databinding.ActivityBuscaunicoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        viewModel.characterResult.observe(this,{
            bindValues(it)
        })
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
            binding.tvNomepersonagem.text = "Name: ${name}"
            binding.tvAlturapersonagem.text = "Height: ${height}"
            binding.tvOlhopersonagem.text = "Eye Color: ${eye_Color}"
        }

    }
}