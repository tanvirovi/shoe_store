package com.tanvir.shoestore.ui.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.tanvir.shoestore.R
import com.tanvir.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var viewModel: WelcomeViewModel
    private lateinit var viewModelFactory: WelcomeViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentWelcomeBinding>(
            inflater,
            R.layout.fragment_welcome,
            container,
            false
        )

        val welcomeFragmentArgs by navArgs<WelcomeFragmentArgs>()

        viewModelFactory = WelcomeViewModelFactory(welcomeFragmentArgs.userEmail)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WelcomeViewModel::class.java)
        binding.welcomeViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.moveToNext.observe(viewLifecycleOwner, Observer { readInstruction ->
            if (readInstruction){
                findNavController()
                    .navigate(WelcomeFragmentDirections
                        .actionWelcomeFragmentToInstructionFragment2())
            }
        })

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.movingToInstruction()
    }
}