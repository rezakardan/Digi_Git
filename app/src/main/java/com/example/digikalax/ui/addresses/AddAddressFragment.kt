package com.example.digikalax.ui.addresses

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digikalax.R
import com.example.digikalax.data.datastore.SessionManager
import com.example.digikalax.data.models.addresses.Address
import com.example.digikalax.data.models.addresses.BodyItemSaveAddress
import com.example.digikalax.data.models.addresses.BodyResponseSaveUserAddress
import com.example.digikalax.databinding.FragmentAddAddressBinding
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.viewmodel.addresses.AddressesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AddAddressFragment : Fragment() {

    lateinit var binding: FragmentAddAddressBinding


    private val viewModel by viewModels<AddressesViewModel>()


    @Inject
    lateinit var sessionManager: SessionManager



    var token=""



    @Inject
    lateinit var addressAdapter: AddressAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAddAddressBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


lifecycleScope.launch {




        sessionManager.getUserToken.first()?.let {





                token =it

Log.e("token", token)



        }









        }


        binding.btnAddAddress.setOnClickListener {




      val ostan=      binding.ostan.text.toString()

        val city=    binding.city.text.toString()

         val street=   binding.street.text.toString()

         val postalCode=   binding.postalCode.text.toString()


            val pelak=  "${binding.pelak.text}"


            val user=   binding.user.text.toString()


            val phone=   binding.phone.text.toString()


            if (ostan.isNotEmpty()&&city.isNotEmpty()&& street.isNotEmpty()&&postalCode.isNotEmpty() && pelak.isNotEmpty() &&user.isNotEmpty() &&phone.isNotEmpty()){




val address="${ostan} -  ${city}: خیابان- ${street}: پلاک - ${pelak}  "

Address(ostan, city, street, postalCode, pelak, user, phone)



                    viewModel.callSaveUserAddresses(BodyResponseSaveUserAddress(token,address, postalCode, phone, user))






                    findNavController().popBackStack()










            }




        }


        loadSaveAddress()



    }

    private fun loadSaveAddress() {
        viewModel.saveUserAddressessLiveData.observe(viewLifecycleOwner){response->




            when(response){


                is NetworkRequest.Loading->{



                }


                is NetworkRequest.Success->{





                    

                    findNavController().popBackStack()


                }


                is NetworkRequest.Error->{
                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }




            }

        }
    }






}