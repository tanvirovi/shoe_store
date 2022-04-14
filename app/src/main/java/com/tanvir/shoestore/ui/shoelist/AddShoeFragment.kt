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

        val afterTextChangedListener = (object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                viewModel.shoeDataChanged(
                    binding.shoeName.text.toString(),
                    binding.brandName.text.toString(),
                    binding.shoeSize.text.toString(),
                    binding.shoeDescription.text.toString(),
                )
            }

        })

        binding.shoeName.addTextChangedListener(afterTextChangedListener)
        binding.brandName.addTextChangedListener(afterTextChangedListener)
        binding.shoeSize.addTextChangedListener(afterTextChangedListener)
        binding.shoeDescription.addTextChangedListener(afterTextChangedListener)

        binding.button2.setOnClickListener {

            viewModel.saveShoesDetails(
                binding.shoeName.text.toString(),
                binding.brandName.text.toString(),
                binding.shoeSize.text.toString().toInt(),
                binding.shoeDescription.text.toString()
            )
        }

        viewModel.editTextState.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.button2.isEnabled = it

            }
        })

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