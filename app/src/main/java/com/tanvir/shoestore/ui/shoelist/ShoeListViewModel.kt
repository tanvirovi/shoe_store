package com.tanvir.shoestore.ui.shoelist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel : ViewModel() {
    private val _listOfShoes : MutableLiveData<List<Shoes>> = MutableLiveData(listOf())
    val listOfShoes : LiveData<List<Shoes>>
        get() = _listOfShoes

    private val _saveOrCancel = MutableLiveData<Boolean>()
    val saveOrCancel : LiveData<Boolean>
        get() = _saveOrCancel

    private val _stateOfFav = MutableLiveData<Boolean>()
    val stateOfFav : LiveData<Boolean>
        get() = _stateOfFav

    // creating two way data
    val editTextName = MutableLiveData<String>()
    val editTextBrand = MutableLiveData<String>()
    val editTextSize = MutableLiveData<String>()
    val editTextDescription = MutableLiveData<String>()

    init {
        _saveOrCancel.value = false
        _stateOfFav.value = false
    }

    fun saveShoesDetails(){
        _listOfShoes.value = _listOfShoes.value?.plus(Shoes(editTextName.value, editTextBrand.value,
            editTextSize.value?.toInt(), editTextDescription.value)) ?: listOf(Shoes(editTextName.value, editTextBrand.value,
            editTextSize.value?.toInt(), editTextDescription.value))

        _saveOrCancel.value = true
        _stateOfFav.value = false

    }

    fun cancel(){
        _saveOrCancel.value = true
        _stateOfFav.value = false
    }

    fun changeState(){
        _saveOrCancel.value = false
        _stateOfFav.value = false
    }

    fun addNewShoes(){
        _stateOfFav.value = true
    }

}