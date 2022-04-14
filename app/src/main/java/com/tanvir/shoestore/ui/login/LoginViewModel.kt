package com.tanvir.shoestore.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tanvir.shoestore.R

class LoginViewModel : ViewModel() {
    // The current word
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginForm : LiveData<LoginFormState>
        get() = _loginForm

    private val _onLoginSuccess = MutableLiveData<Boolean>()
    val onLoginSuccess : LiveData<Boolean>
        get() = _onLoginSuccess

    init {
        _onLoginSuccess.value = false
    }

    fun successFullLogin() {
        _onLoginSuccess.value = true

    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isBothDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            if (Patterns.EMAIL_ADDRESS.matcher(username).matches()){
                _loginForm.value = LoginFormState(isUsernameValid = true)
                true
            }else{
                false
            }

        } else {
            false
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return if (password.length > 5){
            _loginForm.value = LoginFormState(isPasswordValid = true)
            true
        }else{
            false
        }
    }
}