package com.example.activitystarwapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.activitystarwapp.data.model.StarshipModel
import com.example.activitystarwapp.data.service.RetroFit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TodosStarshipsViewModel : ViewModel() {
    private val endpoint = RetroFit.setRetrofit()
    val starshipList = MutableLiveData<StarshipModel.Response>()
    private var flag = 0
    fun setUpList(){
        if(flag == 1)
            return
        starshipList.value = StarshipModel.Response(0, "", null, listOf())
        flag = 1
    }

    fun getStarships() {

        CoroutineScope(Dispatchers.IO).launch {
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
