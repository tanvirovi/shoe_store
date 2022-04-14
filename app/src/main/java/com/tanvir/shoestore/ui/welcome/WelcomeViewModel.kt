package com.tanvir.shoestore.ui.welcome

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel(userEmail :String) : ViewModel(){
    private val _userEmail = MutableLiveData<String>()
    val userEmail : LiveData<String>
        get() = _userEmail

    private val _moveToNext = MutableLiveData<Boolean>()
    val moveToNext : LiveData<Boolean>
        get() = _moveToNext

    init {
        Log.e("WelcomeFragment", userEmail)
        _moveToNext.value = false
        _userEmail.value = userEmail
    }

    fun moveToInstruction(){
        _moveToNext.value = true
    }
    fun movingToInstruction(){
        _moveToNext.value = false
    }
}