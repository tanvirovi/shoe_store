package com.tanvir.shoestore.ui.shoelist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tanvir.shoestore.R
import com.tanvir.shoestore.databinding.FragmentAddShoeBinding

class AddShoeFragment : Fragment() {

    private lateinit var binding:FragmentAddShoeBinding
    private lateinit var viewModel : ShoeListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_shoe,
            container,
            false
        )
        viewModel = ViewModelProvider(requireActivity())[ShoeListViewModel::class.java]
        binding.addShowViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.saveOrCancel.observe(viewLifecycleOwner, Observer {
            if (it){
                findNavController().navigate(AddShoeFragmentDirections.actionAddShoeFragmentToShoeListFragment())
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.changeState()
    }

}