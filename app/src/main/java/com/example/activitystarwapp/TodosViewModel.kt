package com.example.activitystarwapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodosViewModel : ViewModel (){
    val characterList = MutableLiveData<List<CharacterModel.Result>>()
    val planetList = MutableLiveData<List<PlanetsModel.Result>>()
    val starshipList = MutableLiveData<List<StarshipModel.Result>>()
    private val endpoint = RetroFit.setRetrofit()

    fun getCharacter(){
        val callback = endpoint.getPeoples()

        callback.enqueue(object  : Callback<CharacterModel.Info> {//ViewModel
            override fun onResponse(call: Call<CharacterModel.Info>, response: Response<CharacterModel.Info>) {
                val model = response.body()
                if(model != null){
                        addListCharacter(model.results)
                } else{
                    Log.d("nullApi","API Nula")
                }
            }

            override fun onFailure(call: Call<CharacterModel.Info>, t: Throwable) {
                Log.d("falhou","Deu Ruim")
            }
        })
    }

    fun getPlanets(){
        val callback = endpoint.getPlanets()

        callback.enqueue(object  : Callback<PlanetsModel.Info> {
            override fun onResponse(call: Call<PlanetsModel.Info>, response: Response<PlanetsModel.Info>) {
                val model = response.body()
                if(model != null){
                    addListPlanet(model.results)
                } else{
                    Log.d("nullApi","API Nula")
                }
            }

            override fun onFailure(call: Call<PlanetsModel.Info>, t: Throwable) {
                Log.d("falhou","Deu Ruim")
            }
        })
    }

    fun getStarships(){
        val callback = endpoint.getStarships()

        callback.enqueue(object  : Callback<StarshipModel.Info> {
            override fun onResponse(call: Call<StarshipModel.Info>, response: Response<StarshipModel.Info>) {
                val model = response.body()
                if(model != null){
                    addListStarship(model.results)
                } else{
                    Log.d("nullApi","API Nula")
                }
            }

            override fun onFailure(call: Call<StarshipModel.Info>, t: Throwable) {
                Log.d("falhou","Deu Ruim")
            }
        })
    }


    fun addListCharacter(model: List<CharacterModel.Result>){
        characterList.value = model
    }

    fun addListPlanet(model: List<PlanetsModel.Result>){
        planetList.value = model
    }

    fun addListStarship(model: List<StarshipModel.Result>){
        starshipList.value = model
    }
}