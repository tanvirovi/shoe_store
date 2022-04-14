package com.tanvir.shoestore.ui.instruction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tanvir.shoestore.R
import com.tanvir.shoestore.databinding.ActivityMainBinding
import com.tanvir.shoestore.databinding.FragmentInstructionBinding

class InstructionFragment : Fragment() {

    private lateinit var binding: FragmentInstructionBinding
    private lateinit var  viewModel: InstructionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_instruction,
            container,
            false
        )
        // viewModel and DataBinding
        viewModel = ViewModelProvider(this)[InstructionViewModel::class.java]
        binding.instructionViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToList.observe(viewLifecycleOwner, Observer { it ->
            if (it){
                findNavController().navigate(InstructionFragmentDirections.actionInstructionFragment2ToShoeListFragment())
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.startShoeList()
    }
}