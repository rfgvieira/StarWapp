package com.example.planetas.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.PlanetsModel
import com.example.planetas.Endpoint
import com.example.service.RetroFit
import kotlinx.coroutines.launch

class PlanetasViewModel : ViewModel() {
    private val retroFit = RetroFit.getRetrofitInstance("https://swapi.dev/api/")
    private val endpoint = retroFit.create(Endpoint :: class.java)
    val planetList = MutableLiveData<PlanetsModel.Response>()


    fun setUpList(){
        planetList.value = PlanetsModel.Response(0, "", null, listOf())
    }

    fun getPlanets(){
        planetList.value = PlanetsModel.Response(0, "", null, listOf())
        viewModelScope.launch {
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