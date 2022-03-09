package com.example.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.service.RetroFit

open class BaseViewModel : ViewModel(){
    val retroFit = RetroFit.getRetrofitInstance("https://swapi.dev/api/")

    private var _adapterClick = MutableLiveData<Boolean>(true)
    val adapterClick : LiveData<Boolean> = _adapterClick

    private var _adapterPosition = MutableLiveData<Int>()
    val adapterPosition : LiveData<Int> = _adapterPosition



    fun adapterSwitch(position : Int){
        _adapterPosition.postValue(position)
        _adapterClick.value?.let {
            _adapterClick.postValue(!it)
        }
    }
}