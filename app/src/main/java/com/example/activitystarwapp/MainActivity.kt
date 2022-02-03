package com.example.activitystarwapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitystarwapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var characterList : ArrayList<CharacterModel.Result> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun getData(){
        val retroFit = RetroFit.getRetrofitInstance("https://swapi.dev/api/")
        val endpoint = retroFit.create(Endpoint ::class.java)
        val callback = endpoint.getPeople()

        callback.enqueue(object  : Callback<CharacterModel.Info> {
            override fun onResponse(call: Call<CharacterModel.Info>, response: Response<CharacterModel.Info>) {
                val model = response.body()
                if(model != null){
                    model.results.forEach {
                        addList(it)
                    }
                    setUpAdapter()
                } else{
                    Log.d("nullApi","API Nula")
                }
            }

            override fun onFailure(call: Call<CharacterModel.Info>, t: Throwable) {
                Log.d("falhou","Deu Ruim")
            }
        })
    }

    private fun addList(model: CharacterModel.Result){
        characterList.add(model)
    }

    private fun setUpAdapter(){
        var adapter = CharacterAdapter(characterList,this)
        binding.rvPersonagens.adapter = adapter
        binding.rvPersonagens.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }
}