package com.example.activitystarwapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.activitystarwapp.data.model.CharacterModel
import com.example.activitystarwapp.data.model.PlanetsModel
import com.example.activitystarwapp.data.model.StarshipModel
import com.example.activitystarwapp.data.service.RetroFit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RandomViewModel : ViewModel() {

    private val endpoint = RetroFit.setRetrofit()
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