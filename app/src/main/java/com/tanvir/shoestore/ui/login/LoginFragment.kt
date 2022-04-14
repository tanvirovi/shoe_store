package com.tanvir.shoestore.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tanvir.shoestore.R
import com.tanvir.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.onLoginSuccess.observe(viewLifecycleOwner, Observer { validCredential ->
            if (validCredential){
                val userEmail = binding.username.editText?.text.toString()
                Log.e("LoginFragment", userEmail)
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(userEmail))
            }
        })


        val afterTextChangedListener = (object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                viewModel.loginDataChanged(
                    binding.username.editText?.text.toString(),
                    binding.password.editText?.text.toString()
                )
            }

        })

        binding.username.editText?.addTextChangedListener(afterTextChangedListener)
        binding.password.editText?.addTextChangedListener(afterTextChangedListener)

        viewModel.loginForm.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                if (loginFormState.isBothDataValid){
                    binding.loginButton.isEnabled = true
                    binding.signupButton.isEnabled = true
                    binding.username.error = null
                    binding.password.error = null
                }else{
                    if (loginFormState.isUsernameValid){
                        binding.username.error = null
                    }else{
                        loginFormState.usernameError?.let {
                            binding.username.error = getString(it)
                        }
                    }
                    if (loginFormState.isPasswordValid){
                        binding.password.error = null
                    }else{
                        loginFormState.passwordError?.let {
                            binding.password.error = getString(it)
                        }
                    }
                }
            })

        return binding.root
    }
}