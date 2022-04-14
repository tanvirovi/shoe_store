package com.tanvir.shoestore.ui.instruction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructionViewModel : ViewModel() {

    private val _navigateToList = MutableLiveData<Boolean>()
    val navigateToList : LiveData<Boolean>
        get() = _navigateToList

    fun finishedReading(){
        _navigateToList.value = true
    }
    fun startShoeList(){
        _navigateToList.value = false
    }

}