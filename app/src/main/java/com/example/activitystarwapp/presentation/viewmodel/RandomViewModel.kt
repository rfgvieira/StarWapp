package com.example.activitystarwapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.activitystarwapp.data.model.CharacterModel
import com.example.activitystarwapp.data.model.PlanetsModel
import com.example.activitystarwapp.data.model.StarshipModel
import com.example.activitystarwapp.data.service.RetroFit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RandomViewModel : ViewModel() {

    private val endpoint = RetroFit.setRetrofit()
    val characterList = MutableLiveData<CharacterModel.Result>()
    val planetList = MutableLiveData<PlanetsModel.Result>()
    val starshipList = MutableLiveData<StarshipModel.Result>()




    fun getCharacter(){
        val callback = endpoint.getPeopleId((1..82).random())

        callback.enqueue(object  : Callback<CharacterModel.Result> {//ViewModel
            override fun onResponse(call: Call<CharacterModel.Result>, response: Response<CharacterModel.Result>) {
                val model = response.body()
                if(model != null){
                    characterList.value = model
                } else{
                    Log.d("nullApi","API Nula")
                }
            }

            override fun onFailure(call: Call<CharacterModel.Result>, t: Throwable) {
                Log.d("falhou","Deu Ruim")
            }
        })
    }

    fun getPlanet(){
        val callback = endpoint.getPlanetsId((1..60).random())

        callback.enqueue(object  : Callback<PlanetsModel.Result> {//ViewModel
            override fun onResponse(call: Call<PlanetsModel.Result>, response: Response<PlanetsModel.Result>) {
                val model = response.body()
                if(model != null){
                    planetList.value = model
                } else{
                    Log.d("nullApi","API Nula")
                }
            }

            override fun onFailure(call: Call<PlanetsModel.Result>, t: Throwable) {
                Log.d("falhou","Deu Ruim")
            }
        })
    }

    fun getStarship(){
        val callback = endpoint.getStarshipsId((1..23).random())

        callback.enqueue(object  : Callback<StarshipModel.Result> {//ViewModel
            override fun onResponse(call: Call<StarshipModel.Result>, response: Response<StarshipModel.Result>) {
                val model = response.body()
                if(model != null){
                    starshipList.value = model
                } else{
                    Log.d("nullApi","API Nula")
                }
            }
            override fun onFailure(call: Call<StarshipModel.Result>, t: Throwable) {
                Log.d("falhou","Deu Ruim")
            }
        })
    }


}