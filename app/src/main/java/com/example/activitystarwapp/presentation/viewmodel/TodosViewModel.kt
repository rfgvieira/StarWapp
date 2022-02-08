package com.example.activitystarwapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.activitystarwapp.data.model.CharacterModel
import com.example.activitystarwapp.data.model.PlanetsModel
import com.example.activitystarwapp.data.service.RetroFit
import com.example.activitystarwapp.data.model.StarshipModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodosViewModel : ViewModel (){
    val characterList = MutableLiveData<CharacterModel.Response>()
    val planetList = MutableLiveData<PlanetsModel.Response>()
    val starshipList = MutableLiveData<StarshipModel.Response>()
    private val endpoint = RetroFit.setRetrofit()

    fun setUpLists(){
        characterList.value = CharacterModel.Response(0, "", null, listOf())
        planetList.value = PlanetsModel.Response(0, "", null, listOf())
        starshipList.value = StarshipModel.Response(0, "", null, listOf())
    }

    fun getCharacter(){
        val callback = endpoint.getPeoples()

        callback.enqueue(object  : Callback<CharacterModel.Response> {//ViewModel
            override fun onResponse(call: Call<CharacterModel.Response>, response: Response<CharacterModel.Response>) {
                val model = response.body()
                if(model != null){
                        addListCharacter(model)
                } else{
                    Log.d("nullApi","API Nula")
                }
            }

            override fun onFailure(call: Call<CharacterModel.Response>, t: Throwable) {
                Log.d("falhou","Deu Ruim")
            }
        })
    }

    fun getPlanets(){
        val callback = endpoint.getPlanets()

        callback.enqueue(object  : Callback<PlanetsModel.Response> {
            override fun onResponse(call: Call<PlanetsModel.Response>, response: Response<PlanetsModel.Response>) {
                val model = response.body()
                if(model != null){
                    addListPlanet(model)
                } else{
                    Log.d("nullApi","API Nula")
                }
            }

            override fun onFailure(call: Call<PlanetsModel.Response>, t: Throwable) {
                Log.d("falhou","Deu Ruim")
            }
        })
    }

    fun getStarships(){
        val callback = endpoint.getStarships()

        callback.enqueue(object  : Callback<StarshipModel.Response> {
            override fun onResponse(call: Call<StarshipModel.Response>, response: Response<StarshipModel.Response>) {
                val model = response.body()
                if(model != null){
                    addListStarship(model)
                } else{
                    Log.d("nullApi","API Nula")
                }
            }

            override fun onFailure(call: Call<StarshipModel.Response>, t: Throwable) {
                Log.d("falhou","Deu Ruim")
            }
        })
    }


    fun addListCharacter(model: CharacterModel.Response){
        characterList.value = model
    }

    fun addListPlanet(model: PlanetsModel.Response){
        planetList.value = model
    }

    fun addListStarship(model: StarshipModel.Response){
        starshipList.value = model
    }
}