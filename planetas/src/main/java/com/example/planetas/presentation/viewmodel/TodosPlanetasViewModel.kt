package com.example.planetas.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planetas.data.api.Endpoint
import com.example.planetas.data.model.PlanetsModel
import com.example.services.RetroFit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodosPlanetasViewModel : ViewModel() {
    val planetList = MutableLiveData<PlanetsModel.Response>()
    private val retroFit = RetroFit.getRetrofitInstance("https://swapi.dev/api/")
    private val endpoint = retroFit.create(Endpoint ::class.java)

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