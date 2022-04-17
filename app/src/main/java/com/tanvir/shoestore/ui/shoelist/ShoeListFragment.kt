package com.tanvir.shoestore.ui.shoelist

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tanvir.shoestore.R
import com.tanvir.shoestore.databinding.ShoeListFragmentBinding
import com.tanvir.shoestore.databinding.ViewShowHolderBinding

class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoeListViewModel
    private lateinit var binding:ShoeListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_list_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(requireActivity())[ShoeListViewModel::class.java]
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel = ViewModelProvider(requireActivity())[ShoeListViewModel::class.java]
        setHasOptionsMenu(true)

        viewModel.stateOfFav.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToAddShoeFragment())
            }
        }

        viewModel.listOfShoes.observe(viewLifecycleOwner) { shoeList ->

            for (shoe in shoeList) {
                addTextView(shoe)
            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.LoginFragment -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
       findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
    }

    private fun addTextView(shoeDetails : Shoes) {

        val layouts : ViewShowHolderBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.view_show_holder,binding.listOfShoes,false)
        layouts.viewModel = viewModel
        layouts.lifecycleOwner = this

        layouts.shoeName.text = getString(R.string.shoe_name_text ,shoeDetails.name)
        layouts.shoeBrand.text = getString(R.string.shoe_brand_text, shoeDetails.company)
        layouts.shoeSize.text = getString(R.string.shoe_size_text, shoeDetails.shoeSize.toString())
        layouts.shoeDetails.text = getString(R.string.shoe_details_text, shoeDetails.description)

        // Add TextView to LinearLayout
        binding.listOfShoes.addView(layouts.root)
    }

}