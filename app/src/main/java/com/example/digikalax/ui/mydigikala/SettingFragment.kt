package com.example.digikalax.ui.mydigikala

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.digikalax.R
import com.example.digikalax.data.datastore.SessionManager
import com.example.digikalax.data.models.basket.StoreProduct
import com.example.digikalax.databinding.FragmentSettingBinding
import com.example.digikalax.utils.constants.Constants.DIGI_BUG
import com.example.digikalax.utils.constants.Constants.DIGI_FAQ
import com.example.digikalax.utils.constants.Constants.DIGI_PRIVACY
import com.example.digikalax.utils.constants.Constants.DIGI_SCORE
import com.example.digikalax.utils.constants.Constants.DIGI_TERMS
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : Fragment() {

    lateinit var binding: FragmentSettingBinding


    @Inject
    lateinit var dataStore: SessionManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)









binding.close.setOnClickListener {


    findNavController().popBackStack()




}






        binding.constDigiPlusLay.setOnClickListener {


            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(DIGI_FAQ))
            startActivity(intent)


        }






        binding.constDigiFavorite.setOnClickListener {


            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(DIGI_PRIVACY))
            startActivity(intent)


        }



        binding.constDigiPlusLayNazarat.setOnClickListener {


            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(DIGI_TERMS))
            startActivity(intent)


        }


        binding.constDigiLayUserInformationn.setOnClickListener {


            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(DIGI_BUG))
            startActivity(intent)


        }




        binding.constDigiLayUserInformationnn.setOnClickListener {


            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(DIGI_SCORE))
            startActivity(intent)


        }


//        binding.constSignOut.setOnClickListener {
//
//
//
//            lifecycleScope.launch {
//
//
//
//            dataStore.saveToken("")
//
//                dataStore.saveUserId("")
//                dataStore.saveUserPassword("null")
//                dataStore.savePhoneNumber("null")
//
//findNavController().navigate(R.id.myDigiKalaFragment)
//
//
//
//
//            }
//        }


    }


}