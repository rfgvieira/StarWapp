package com.example.espaconave.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.espaconave.Endpoint
import com.example.model.StarshipModel
import com.example.service.RetroFit
import kotlinx.coroutines.launch


class StarshipsViewModel : ViewModel() {
    private val retroFit = RetroFit.getRetrofitInstance("https://swapi.dev/api/")
    private val endpoint = retroFit.create(Endpoint :: class.java)
    val starshipList = MutableLiveData<StarshipModel.Response>()
    private var flag = 0
    fun setUpList(){
        if(flag == 1)
            return
        starshipList.value = StarshipModel.Response(0, "", null, listOf())
        flag = 1
    }

    fun getStarships() {

        viewModelScope.launch {
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
