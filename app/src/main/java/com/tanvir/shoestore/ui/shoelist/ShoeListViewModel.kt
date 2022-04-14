package com.tanvir.shoestore.ui.shoelist

import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tanvir.shoestore.R
import com.tanvir.shoestore.ui.login.LoginFormState

class ShoeListViewModel : ViewModel() {
    private val _listOfShoes : MutableLiveData<List<Shoes>> = MutableLiveData(listOf())
    val listOfShoes : LiveData<List<Shoes>>
        get() = _listOfShoes

    private val _saveOrCancel = MutableLiveData<Boolean>()
    val saveOrCancel : LiveData<Boolean>
        get() = _saveOrCancel

    private val _editTextState = MutableLiveData<Boolean>()
    val editTextState : LiveData<Boolean>
        get() = _editTextState

    init {
        _saveOrCancel.value = false
        _editTextState.value = false
    }

    fun saveShoesDetails(name: String, companyName: String, size: Int, description: String){
        _listOfShoes.value = _listOfShoes.value?.plus(Shoes(name, companyName, size, description)) ?: listOf(Shoes(name, companyName, size, description))
        _saveOrCancel.value = true
    }

    fun cancel(){
        _saveOrCancel.value = true
    }
    fun changeState(){
        _saveOrCancel.value = false
        _editTextState.value =  false
    }

    fun shoeDataChanged(name: String, companyName: String, size: String, description: String) {
        _editTextState.value = !(name.isEmpty() || companyName.isEmpty() || description.isEmpty() || size.isEmpty())
    }

}