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

class ShoeListViewModel : ViewModel() {
    private val _listOfShoes : MutableLiveData<List<Shoes>> = MutableLiveData(listOf())
    val listOfShoes : LiveData<List<Shoes>>
        get() = _listOfShoes


    val data = Shoes()

    fun saveShoesDetails(name: String, companyName: String, size: Int, description: String){
//        _listOfShoes.value = mutableListOf(Shoes(name, companyName, size, description))
        _listOfShoes.value = _listOfShoes.value?.plus(Shoes(name, companyName, size, description)) ?: listOf(Shoes(name, companyName, size, description))
        Log.e("ShoeListViewModel", _listOfShoes.toString())

    }

}