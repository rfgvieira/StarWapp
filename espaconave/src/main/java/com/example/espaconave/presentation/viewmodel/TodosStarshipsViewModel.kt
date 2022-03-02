package com.example.espaconave.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.espaconave.data.model.StarshipModel
import com.example.espaconave.data.api.Endpoint
import com.example.services.RetroFit
import kotlin.coroutines.CoroutineContext


class TodosStarshipsViewModel : ViewModel() {
    private val retroFit = RetroFit.getRetrofitInstance("https://swapi.dev/api/")
    private val endpoint = retroFit.create(Endpoint ::class.java)
    val starshipList = MutableLiveData<StarshipModel.Response>()

    fun getStarships() {
        CoroutineScope.launch {
            val response = endpoint.getStarships()
            if (response.isSuccessful) {
                val model = response.body()
                if (model != null)
                    starshipList.postValue(model)
                else
                    Log.d("nullApi", "API Nula")
            } else
                Log.d("falhou", "Deu Ruim")
        }
    }
}
