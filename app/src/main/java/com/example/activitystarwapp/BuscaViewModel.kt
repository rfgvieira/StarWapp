package com.example.activitystarwapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuscaViewModel : ViewModel(){

    val characterResult = MutableLiveData<CharacterModel.Result>()

    fun getData(id : Int){
        val endpoint = RetroFit.setRetrofit()
        val callback = endpoint.getPeopleId(id)

        callback.enqueue(object  : Callback<CharacterModel.Result> {
            override fun onResponse(call: Call<CharacterModel.Result>, response: Response<CharacterModel.Result>) {
                val model = response.body()
                if(model != null){
                    characterResult.value = model
                } else{
                    Log.d("nullApi","API Nula")
                }
            }

            override fun onFailure(call: Call<CharacterModel.Result>, t: Throwable) {
                Log.d("falhou","Deu Ruim")
            }
        })
    }
}