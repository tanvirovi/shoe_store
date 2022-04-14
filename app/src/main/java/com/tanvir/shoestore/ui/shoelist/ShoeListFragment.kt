package com.tanvir.shoestore.ui.shoelist

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tanvir.shoestore.R
import com.tanvir.shoestore.databinding.ShoeListFragmentBinding

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
        setHasOptionsMenu(true)

        binding.floatingActionButton2.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToAddShoeFragment())
        }

        viewModel.listOfShoes.observe(viewLifecycleOwner, Observer { shoeList ->

            for (shoe in shoeList) {
                addTextView(shoe)
            }
        })

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


    @SuppressLint("NewApi")
    private fun addTextView(shoeDetails : Shoes) {
        val layouts = LayoutInflater.from(requireContext()).inflate(R.layout.view_show_holder,binding.listOfShoes,false)
        // Create TextView programmatically.
        val textViewName: TextView = layouts.requireViewById(R.id.shoe_name)
        val textViewCompany: TextView = layouts.requireViewById(R.id.shoe_brand)
        val textViewSize: TextView = layouts.requireViewById(R.id.shoe_size)
        val textViewDescription: TextView = layouts.requireViewById(R.id.shoe_details)
        textViewName.text = getString(R.string.shoe_name_text ,shoeDetails.name)
        textViewCompany.text = getString(R.string.shoe_brand_text, shoeDetails.company)
        textViewSize.text = getString(R.string.shoe_size_text, shoeDetails.shoeSize.toString())
        textViewDescription.text = getString(R.string.shoe_details_text, shoeDetails.description)

        // Add TextView to LinearLayout
        binding.listOfShoes.addView(layouts)
    }

}