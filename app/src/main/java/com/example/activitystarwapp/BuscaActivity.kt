package com.example.activitystarwapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.activitystarwapp.databinding.ActivityBuscaunicoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuscaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuscaunicoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscaunicoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btnIdpersonagem.setOnClickListener {
            getData()
        }
    }

    private fun getData(){
        val id : Int = binding.edtInputId.text.toString().toInt()

        val retroFit = RetroFit.getRetrofitInstance("https://swapi.dev/api/")
        val endpoint = retroFit.create(Endpoint ::class.java)
        val callback = endpoint.getPeopleId(id)

        callback.enqueue(object  : Callback<CharacterModel.Result> {
            override fun onResponse(call: Call<CharacterModel.Result>, response: Response<CharacterModel.Result>) {
                val model = response.body()
                if(model != null){
                    bindValues(model)
                } else{
                    Log.d("nullApi","API Nula")
                }
            }

            override fun onFailure(call: Call<CharacterModel.Result>, t: Throwable) {
                Log.d("falhou","Deu Ruim")
            }
        })
    }

    private fun bindValues(model : CharacterModel.Result){
        with(model){
            binding.tvNomepersonagem.text = "Name: ${name}"
            binding.tvAlturapersonagem.text = "Height: ${height}"
            binding.tvOlhopersonagem.text = "Eye Color: ${eye_Color}"
        }

    }
}