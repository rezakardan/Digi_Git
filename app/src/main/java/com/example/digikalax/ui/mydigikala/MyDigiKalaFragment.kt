package com.example.digikalax.ui.mydigikala

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.digikalax.R
import com.example.digikalax.data.datastore.SessionManager
import com.example.digikalax.data.models.mydigikala.BodyResponseLogin
import com.example.digikalax.databinding.FragmentMyDigiKalaBinding
import com.example.digikalax.utils.network.NetworkChecker
import com.example.digikalax.viewmodel.mydigikala.MyDigiKalaViewModel
import com.example.digikalax.viewmodel.mydigikala.ScreenState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MyDigiKalaFragment : Fragment() {


    @Inject
    lateinit var bodyResponseLogin: BodyResponseLogin


    @Inject
    lateinit var sessionManager: SessionManager


    var phoneNumber = ""



    var token=""










    lateinit var binding: FragmentMyDigiKalaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyDigiKalaBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)











        lifecycleScope.launch {



            sessionManager.getUserToken.first()?.let {


                 token=it



            }



            if (token.isNotEmpty()){




                findNavController().navigate(R.id.action_myDigiKalaFragment_to_profileFragment)

                binding.buttonNumber.alpha=0.5f
                binding.buttonNumber.isEnabled=false


                binding.phoneNumberEdt.isEnabled=false


            }else{



                binding.buttonNumber.setOnClickListener {

                    phoneNumber=binding.phoneNumberEdt.text.toString()


                    if (phoneNumber.isNotEmpty()&& phoneNumber.length==11){

                        val direction=MyDigiKalaFragmentDirections.actionMyDigiKalaFragmentToRegisterFragment(phoneNumber)

                        findNavController().navigate(direction)











                    }else{

                        Toast.makeText(context, "شماره موبایل خود را درست وارد کنید", Toast.LENGTH_SHORT).show()

                    }




                }






            }

        }












}














}


