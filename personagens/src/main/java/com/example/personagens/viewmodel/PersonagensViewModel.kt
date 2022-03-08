package com.example.personagens.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.CharacterModel
import com.example.personagens.Endpoint
import com.example.service.RetroFit
import kotlinx.coroutines.launch

class PersonagensViewModel: ViewModel() {
    private val retroFit = RetroFit.getRetrofitInstance("https://swapi.dev/api/")
    private val endpoint = retroFit.create(Endpoint :: class.java)
    val characterList = MutableLiveData<CharacterModel.Response>()

    fun setUpList(){
        characterList.value = CharacterModel.Response(0, "", null, listOf())
    }



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