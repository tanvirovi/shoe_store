package com.tanvir.shoestore.ui.login

data class LoginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isUsernameValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isBothDataValid: Boolean = false
)
