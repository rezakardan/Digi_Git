package com.example.digikalax.ui.mydigikala

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
import androidx.navigation.fragment.navArgs
import com.example.digikalax.R
import com.example.digikalax.data.datastore.SessionManager
import com.example.digikalax.data.models.mydigikala.BodyResponseLogin
import com.example.digikalax.databinding.FragmentRegisterBinding
import com.example.digikalax.utils.constants.Constants
import com.example.digikalax.utils.network.NetworkChecker
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.viewmodel.mydigikala.MyDigiKalaViewModel
import com.example.digikalax.viewmodel.mydigikala.ScreenState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class RegisterFragment : Fragment() {


    lateinit var binding: FragmentRegisterBinding


    @Inject
    lateinit var isNetworkChecker: NetworkChecker

    var isNetwork=false


    private val viewModel by viewModels<MyDigiKalaViewModel> ()

    @Inject
    lateinit var bodyResponseLogin: BodyResponseLogin



    @Inject
    lateinit var sessionManager: SessionManager


var phoneNumber=""
    var password=""




    private val args by navArgs<RegisterFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        args.let {
           phoneNumber=it.phonenumber

        }





        lifecycleScope.launch {



            isNetworkChecker.checkNetwork().collect{

                isNetwork=it



            }
        }



            binding.phoneNumberEdt.setText(phoneNumber)




        binding.buttonNumber.setOnClickListener {


            val password=binding.passwordEdt.text.toString()

            if (password.isNotEmpty() && password.length>=6){


                bodyResponseLogin.password=password

bodyResponseLogin.phone=phoneNumber




                if (isNetwork){





                viewModel.callLoginVerify(bodyResponseLogin)

                }


            }





        }






        loadCallLoginVerify()



    }

    private fun loadCallLoginVerify() {
        viewModel.loginAndVerifyLiveData.observe(viewLifecycleOwner){response->





            when(response){


                is NetworkRequest.Loading->{}


                is NetworkRequest.Success->{


                    response.data?.data?.let { data->


                        lifecycleScope.launch {




                       sessionManager.saveToken(data.token)

                            sessionManager.saveUserPassword(password)

                            sessionManager.savePhoneNumber(phoneNumber)

                            sessionManager.saveUserName(data.id)

Log.e("1111","session :${sessionManager}")




                            findNavController().navigate(R.id.action_registerFragment_to_profileFragment)

                        }




                       // findNavController().popBackStack(R.id.registerFragment,true)
                       // findNavController().popBackStack(R.id.myDigiKalaFragment,true)


                    }








                }


                is NetworkRequest.Error->{
                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }



            }





        }
    }


}