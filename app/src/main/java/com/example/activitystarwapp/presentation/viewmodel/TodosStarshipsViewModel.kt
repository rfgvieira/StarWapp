package com.example.activitystarwapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.activitystarwapp.data.model.StarshipModel
import com.example.activitystarwapp.data.service.RetroFit
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

    fun getStarships(){
        val callback = endpoint.getStarships()

        callback.enqueue(object  : Callback<StarshipModel.Response> {
            override fun onResponse(call: Call<StarshipModel.Response>, response: Response<StarshipModel.Response>) {
                val model = response.body()
                if(model != null){
                    addListStarship(model)
                } else{
                    Log.d("nullApi","API Nula")
                }
            }

            override fun onFailure(call: Call<StarshipModel.Response>, t: Throwable) {
                Log.d("falhou","Deu Ruim")
            }
        })
    }


    fun addListStarship(model: StarshipModel.Response){
        starshipList.value = model
    }
}