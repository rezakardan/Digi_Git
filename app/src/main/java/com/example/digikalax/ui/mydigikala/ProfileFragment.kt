package com.example.digikalax.ui.mydigikala

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.digikalax.R
import com.example.digikalax.data.datastore.SessionManager
import com.example.digikalax.data.models.mydigikala.BodyResponseLogin
import com.example.digikalax.databinding.FragmentProfileBinding
import com.example.digikalax.ui.mydigikala.adapters.ImagesAdapter
import com.example.digikalax.utils.constants.Constants
import com.example.digikalax.utils.network.NetworkChecker
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.viewmodel.checkout.CheckOutViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewmodel by viewModels<CheckOutViewModel>()

    var userName = ""


    @Inject
    lateinit var bodyResponseLogin: BodyResponseLogin

    @Inject
    lateinit var sessionManager: SessionManager


    @Inject
    lateinit var ordersAdapter: ImagesAdapter
    var phone = ""



    @Inject
    lateinit var isNetworkChecker: NetworkChecker

    var isNetwork=false

var token=""


    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        lifecycleScope.launch {
            sessionManager.getUserToken.first()?.let {

                token=it

                if (isNetwork) {
                    viewmodel.getUserOrders(it)
                }

            }

        }



        if (token.isEmpty()){


            binding.txtUser.text=requireContext().getString(R.string.completion_of_user_information)




        }






        binding.constOrder1.setOnClickListener {


findNavController().navigate(R.id.action_profileFragment_to_unpaidOrderFragment)






        }




        binding.constOrder2.setOnClickListener {


            findNavController().navigate(R.id.action_profileFragment_to_processingOrderFragment)






        }



        binding.constOrder3.setOnClickListener {


            findNavController().navigate(R.id.action_profileFragment_to_myOrdersFragment)






        }

        binding.constOrder4.setOnClickListener {


            findNavController().navigate(R.id.action_profileFragment_to_canceledOrderFragment)






        }

        binding.constOrder5.setOnClickListener {


            findNavController().navigate(R.id.action_profileFragment_to_returnedOrderFragment)






        }


        binding.constDigiFavorite.setOnClickListener {


            findNavController().navigate(R.id.action_profileFragment_to_favoriteProductFragment)


        }




        binding.constDigiPlusLayNazarat.setOnClickListener {

            findNavController().navigate(R.id.action_profileFragment_to_userCommentsFragment)

        }







        binding.constLay22.setOnClickListener {

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.DIGI_WALLET))
            startActivity(intent)


        }


        binding.imgSlider.load(R.drawable.digiclub1)



        binding.imgSlider.setOnClickListener {


            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.DIGI_CLUB))

            startActivity(intent)

        }

        binding.imgSlider.load(R.drawable.digiclub2)



        binding.imgSlider.setOnClickListener {


            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.DIGI_CLUB))

            startActivity(intent)

        }








        binding.constLay23.setOnClickListener {

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.DIGI_CLUB))
            startActivity(intent)


        }


        binding.constDigiPlusLay.setOnClickListener {

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.DIGIPLUS_URL))
            startActivity(intent)


        }







        binding.constLay33.setOnClickListener {


            findNavController().navigate(R.id.action_profileFragment_to_userAccountFragment)


        }










        lifecycleScope.launch {


            sessionManager.getUserName.collect {


                if (it .isNullOrEmpty()) {

                    binding.txtUser.text =
                        requireContext().getString(R.string.completion_of_user_information)


                } else {

                    binding.txtUser.text = it

                }


            }
        }











        binding.constDigiPlusLayAddresses.setOnClickListener {


            findNavController().navigate(R.id.action_profileFragment_to_addressFragment)
        }












        lifecycleScope.launch {


            sessionManager.getUserPhone.first()?.let {

                phone = it.toString()
                binding.txtPhone.text = phone
            }

        }




        Log.e("1111", phone.toString())


        binding.tanzimat.setOnClickListener {


            findNavController().navigate(R.id.action_profileFragment_to_settingFragment)


        }

        binding.close.visibility=View.GONE


        loadGetUserOrders()
    }

    private fun loadGetUserOrders() {
        viewmodel.getUserOrdersLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {


                is NetworkRequest.Loading -> {



                }


                is NetworkRequest.Success -> {


                    response.data?.data?.let { data ->




                    }

                }


                is NetworkRequest.Error -> {

                }


            }


        }
    }


}