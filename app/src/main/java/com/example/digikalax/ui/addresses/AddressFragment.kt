package com.example.digikalax.ui.addresses

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digikalax.R
import com.example.digikalax.data.datastore.SessionManager
import com.example.digikalax.data.models.addresses.BodyItemSaveAddress
import com.example.digikalax.databinding.FragmentAddressBinding
import com.example.digikalax.utils.network.NetworkChecker
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.viewmodel.addresses.AddressesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AddressFragment : Fragment() {


    lateinit var binding: FragmentAddressBinding


    private val viewModel by activityViewModels<AddressesViewModel>()


    @Inject
    lateinit var addressAdapter: AddressAdapter


    @Inject
    lateinit var sessionManager: SessionManager



    @Inject
    lateinit var networkChecker: NetworkChecker


    var token=""

    var isNetwork=false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.back.setOnClickListener {

            findNavController().popBackStack()
        }












        binding.addAddress.setOnClickListener {


           findNavController().navigate(R.id.action_addressFragment_to_addAddressFragment)


        }



        lifecycleScope.launch {


            networkChecker.checkNetwork().collect{


                isNetwork=it



            }
        }




        lifecycleScope.launch {


            sessionManager.getUserToken.first()?.let {


                if (isNetwork){
                    viewModel.callGetUserAddresses(it)

                }


            }
        }





























        loadGetUserAddresses()


    }


    private fun loadGetUserAddresses() {
        viewModel.getUserAddressessLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {


                is NetworkRequest.Loading -> {


                    binding.progressBar.visibility=View.VISIBLE
                    binding.recyclerAddress.visibility=View.GONE

                }


                is NetworkRequest.Success -> {

                    binding.progressBar.visibility=View.GONE
                    binding.recyclerAddress.visibility=View.VISIBLE
                    response.data?.data?.let { data ->


                        if (data.isNotEmpty()) {


                            addressAdapter.setData(data)


                            binding.recyclerAddress.adapter = addressAdapter

                            binding.recyclerAddress.layoutManager = LinearLayoutManager(
                                requireContext(), LinearLayoutManager.VERTICAL, true
                            )

                        } else {
                            binding.recyclerAddress.visibility = View.GONE
                            Toast.makeText(
                                context,
                                "هیچ آدرسی هنوز ثبت نشده است ",
                                Toast.LENGTH_SHORT
                            ).show()


                        }


                    }

                }


                is NetworkRequest.Error -> {
                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }


            }


        }
    }


}