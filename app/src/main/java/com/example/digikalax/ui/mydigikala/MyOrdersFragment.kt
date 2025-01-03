package com.example.digikalax.ui.mydigikala

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digikalax.data.datastore.SessionManager
import com.example.digikalax.databinding.FragmentMyOrdersBinding
import com.example.digikalax.ui.mydigikala.adapters.ImagesAdapter
import com.example.digikalax.ui.mydigikala.adapters.OrdersAdapter
import com.example.digikalax.utils.network.NetworkChecker
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.viewmodel.checkout.CheckOutViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MyOrdersFragment : Fragment() {
    lateinit var binding: FragmentMyOrdersBinding


    private val viewModel by viewModels<CheckOutViewModel>()


    @Inject
    lateinit var ordersAdapter: OrdersAdapter

    @Inject
    lateinit var sessionManager: SessionManager


    @Inject
    lateinit var isNetworkChecker: NetworkChecker

    var isNetwork=false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMyOrdersBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launch {



            isNetworkChecker.checkNetwork().collect{

                isNetwork=it



            }
        }




        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }


        lifecycleScope.launch {

            sessionManager.getUserToken.first()?.let {

if (isNetwork) {
    viewModel.getUserOrders(it)
}

            }

        }

        loadUserOrders()

    }

    private fun loadUserOrders() {
        viewModel.getUserOrdersLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {


                is NetworkRequest.Loading -> {

                }


                is NetworkRequest.Success -> {


                    response.data?.data?.let { data ->

                        if (data[0].orderStatus == "2") {


                            ordersAdapter.setData(data)

                            binding.recyOrder.adapter = ordersAdapter

                            binding.recyOrder.layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.VERTICAL,
                                false
                            )


                        } else {

                            Toast.makeText(requireContext(), "لیست سفارش شما خالی است", Toast.LENGTH_SHORT).show()
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