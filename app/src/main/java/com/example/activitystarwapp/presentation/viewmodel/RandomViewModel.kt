package com.example.activitystarwapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personagem.data.model.CharacterModel
import com.example.planetas.data.model.PlanetsModel
import com.example.espaconave.data.model.StarshipModel
import com.example.planetas.data.api.Endpoint
import com.example.services.RetroFit

class RandomViewModel : ViewModel() {

    private val retroFit = RetroFit.getRetrofitInstance("https://swapi.dev/api/")
    private val endpoint = retroFit.create(Endpoint ::class.java)
    val characterList = MutableLiveData<CharacterModel.Result>()
    val planetList = MutableLiveData<PlanetsModel.Result>()
    val starshipList = MutableLiveData<StarshipModel.Result>()




    fun getCharacter(){

        CoroutineScope(Dispatchers.IO).launch {
            val response = endpoint.getPeopleId((1..82).random())
            if(response.isSuccessful){
                val model = response.body()
                if(model != null)
                    characterList.postValue(model)
                else
                    Log.d("nullApi","API Nula")
            }
            else
                Log.d("falhou","Deu Ruim")

        }
    }

    fun getPlanet(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = endpoint.getPlanetsId((1..60).random())
            if(response.isSuccessful){
                val model = response.body()
                if(model != null)
                    planetList.postValue(model)
                else
                    Log.d("nullApi","API Nula")
            }
            else
                Log.d("falhou","Deu Ruim")
        }
    }

    fun getStarship(){

        CoroutineScope(Dispatchers.IO).launch {
            val response = endpoint.getStarshipsId((1..23).random())
            if(response.isSuccessful){
                val model = response.body()
                if(model != null)
                    starshipList.postValue(model)
                else
                    Log.d("nullApi","API Nula")
            } else
                Log.d("falhou","Deu Ruim")
        }
    }
}