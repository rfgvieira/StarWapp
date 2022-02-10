package com.example.activitystarwapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.activitystarwapp.data.model.CharacterModel
import com.example.activitystarwapp.data.service.RetroFit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class TodosPersonagensViewModel: ViewModel() {
    private val endpoint = RetroFit.setRetrofit()
    val characterList = MutableLiveData<CharacterModel.Response>()


    fun getCharacter(){
        characterList.value = CharacterModel.Response(0, "", null, listOf())

        val callback = endpoint.getPeoples()

        callback.enqueue(object  : Callback<CharacterModel.Response> {//ViewModel
        override fun onResponse(call: Call<CharacterModel.Response>, response: Response<CharacterModel.Response>) {
            val model = response.body()
            if(model != null){
                addListCharacter(model)
            } else{
                Log.d("nullApi","API Nula")
            }
        }

            override fun onFailure(call: Call<CharacterModel.Response>, t: Throwable) {
                Log.d("falhou","Deu Ruim")
            }
        })
    }

    fun addListCharacter(model: CharacterModel.Response){
        characterList.value = model
    }
}