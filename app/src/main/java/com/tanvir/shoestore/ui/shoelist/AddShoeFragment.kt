package com.tanvir.shoestore.ui.shoelist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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

        binding.button2.setOnClickListener {
            Log.e("AddShoeFragment", "it.toString()")

            viewModel.saveShoesDetails(
                binding.shoeName.text.toString(),
                binding.brandName.text.toString(),
                binding.shoeSize.text.toString().toInt(),
                binding.shoeDescription.text.toString()
            )
            findNavController().navigate(AddShoeFragmentDirections.actionAddShoeFragmentToShoeListFragment())
        }


        return binding.root
    }

}