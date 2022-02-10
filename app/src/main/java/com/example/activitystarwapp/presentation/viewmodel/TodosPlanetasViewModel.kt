package com.example.activitystarwapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.activitystarwapp.data.model.PlanetsModel
import com.example.activitystarwapp.data.service.RetroFit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodosPlanetasViewModel : ViewModel() {
    val planetList = MutableLiveData<PlanetsModel.Response>()
    private val endpoint = RetroFit.setRetrofit()


    fun getPlanets(){
        planetList.value = PlanetsModel.Response(0, "", null, listOf())
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

    fun addListPlanet(model: PlanetsModel.Response){
        planetList.value = model
    }
}