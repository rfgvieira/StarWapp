package com.example.personagem.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personagem.data.api.Endpoint
import com.example.personagem.data.model.CharacterModel
import com.example.services.RetroFit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodosPersonagensViewModel: ViewModel() {

    val characterList = MutableLiveData<CharacterModel.Response>()
    private val retroFit = RetroFit.getRetrofitInstance("https://swapi.dev/api/")
    private val endpoint = retroFit.create(Endpoint ::class.java)

    fun getCharacter(){


        characterList.value = CharacterModel.Response(0, "", null, listOf())

        viewModelScope.launch {
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