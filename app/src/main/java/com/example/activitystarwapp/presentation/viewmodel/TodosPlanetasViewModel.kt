package com.example.activitystarwapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.activitystarwapp.data.model.PlanetsModel
import com.example.activitystarwapp.data.model.StarshipModel
import com.example.activitystarwapp.data.service.RetroFit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodosPlanetasViewModel : ViewModel() {
    val planetList = MutableLiveData<PlanetsModel.Response>()
    private val endpoint = RetroFit.setRetrofit()

    fun setUpList(){
        planetList.value = PlanetsModel.Response(0, "", null, listOf())
    }

    fun getPlanets(){
        planetList.value = PlanetsModel.Response(0, "", null, listOf())
        CoroutineScope(Dispatchers.IO).launch {
            val response = endpoint.getPlanets()
            if(response.isSuccessful) {
                val model = response.body()
                if(model != null)
                   planetList.postValue(model)
                else
                    Log.d("nullApi","API Nula")
            } else
                Log.d("falhou","Deu Ruim")
        }
    }
}