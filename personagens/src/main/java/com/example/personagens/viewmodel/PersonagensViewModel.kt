package com.example.personagens.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.model.CharacterModel
import com.example.personagens.Endpoint
import com.example.service.RetroFit
import kotlinx.coroutines.launch

class PersonagensViewModel: BaseViewModel() {
    private val endpoint = retroFit.create(Endpoint :: class.java)

    private var _characterList = MutableLiveData<CharacterModel.Response>()
    val characterList : LiveData<CharacterModel.Response> = _characterList


    fun setUpList(){
        _characterList.value = CharacterModel.Response(0, "", null, listOf())
    }

    fun getCharacter(){
        _characterList.value = CharacterModel.Response(0, "", null, listOf())

        viewModelScope.launch {
            val response = endpoint.getPeoples()
            if(response.isSuccessful) {
                val model = response.body()
                if(model != null)
                    _characterList.postValue(model)
                else
                    Log.d("nullApi","API Nula")
            } else
                Log.d("falhou","Deu Ruim")

        }
    }
}