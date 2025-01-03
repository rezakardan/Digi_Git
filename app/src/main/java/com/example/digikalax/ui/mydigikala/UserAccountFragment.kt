package com.example.digikalax.ui.mydigikala

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
import com.example.digikalax.R
import com.example.digikalax.data.datastore.SessionManager
import com.example.digikalax.data.models.setname.BodyResponseSetName
import com.example.digikalax.databinding.FragmentUserAccountBinding
import com.example.digikalax.utils.network.NetworkChecker
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.viewmodel.mydigikala.MyDigiKalaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UserAccountFragment : Fragment() {

    lateinit var binding: FragmentUserAccountBinding

    var firstName = ""

    var lastName = ""

    @Inject
    lateinit var sessionManager: SessionManager



    @Inject
    lateinit var isNetworkChecker: NetworkChecker

    var isNetwork=false

    var token = ""
    private val myDigiKalaViewModel by activityViewModels<MyDigiKalaViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserAccountBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }



        lifecycleScope.launch {



            isNetworkChecker.checkNetwork().collect{

                isNetwork=it



            }
        }







        lifecycleScope.launch {



            sessionManager.getUserToken.first()?.let {




                token=it

            }

        }


        binding.confirmBtn.setOnClickListener {


        firstName=    binding.firstName.text.toString()

            lastName=binding.lastName.text.toString()




            if (firstName.isNotEmpty() && lastName.isNotEmpty()){


if (isNetwork) {
    myDigiKalaViewModel.callSetName(BodyResponseSetName("$firstName$lastName", token))

}
                lifecycleScope.launch {
                    sessionManager.saveUserName("$firstName$lastName")

                }

            }




        }

        loadSetName()

    }

    private fun loadSetName() {
        myDigiKalaViewModel.setNameLiveData.observe(viewLifecycleOwner){response->




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









