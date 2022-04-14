package com.tanvir.shoestore.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WelcomeViewModelFactory(private val userEmail : String) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)){
            return WelcomeViewModel(userEmail) as T
        }
        throw IllegalArgumentException("Unknown View Model")
    }
}