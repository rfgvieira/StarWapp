package com.example.activitystarwapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activitystarwapp.data.api.Endpoint
import com.example.model.CharacterModel
import com.example.model.PlanetsModel
import com.example.model.StarshipModel
import com.example.service.RetroFit
import kotlinx.coroutines.launch

class RandomViewModel : ViewModel() {

    private val retroFit = RetroFit.getRetrofitInstance("https://swapi.dev/api/")
    private val endpoint = retroFit.create(Endpoint :: class.java)
    val characterList = MutableLiveData<CharacterModel.Result>()
    val planetList = MutableLiveData<PlanetsModel.Result>()
    val starshipList = MutableLiveData<StarshipModel.Result>()




    fun getCharacter(){

        viewModelScope.launch {
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
        viewModelScope.launch {
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

        viewModelScope.launch {
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