package com.example.activitystarwapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.activitystarwapp.data.model.CharacterModel
import com.example.activitystarwapp.data.model.StarshipModel
import com.example.activitystarwapp.data.service.RetroFit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class TodosPersonagensViewModel: ViewModel() {
    private val endpoint = RetroFit.setRetrofit()
    val characterList = MutableLiveData<CharacterModel.Response>()

    fun setUpList(){
        characterList.value = CharacterModel.Response(0, "", null, listOf())
    }



    fun getCharacter(){
        characterList.value = CharacterModel.Response(0, "", null, listOf())

        CoroutineScope(Dispatchers.IO).launch {
            val response = endpoint.getPeoples()
            if(response.isSuccessful) {
                val model = response.body()
                if(model != null)
                    characterList.postValue(model)
                else
                    Log.d("nullApi","API Nula")
            } else
                Log.d("falhou","Deu Ruim")

        }
    }
}