package com.example.digikalax.ui.confirmpurchase

import android.icu.text.DecimalFormat
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
import com.example.digikalax.data.models.confirm.BodyConfirmPurchase
import com.example.digikalax.databinding.FragmentConfirmPurchasesBinding
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.utils.zarinpal.ZarinPalPurchase
import com.example.digikalax.viewmodel.basket.BasketViewModel
import com.example.digikalax.viewmodel.checkout.CheckOutViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ConfirmPurchasesFragment : Fragment() {




    private val args:ConfirmPurchasesFragmentArgs by navArgs()

    private val decimalFormater by lazy { DecimalFormat("##,###.##") }




    var orderSituation=""

    var token=""

    var productId=""

    private val viewModel by viewModels<BasketViewModel>()


    private val checkOutViewModel by viewModels<CheckOutViewModel>()


    @Inject
    lateinit var sessionManager: SessionManager


    lateinit var binding: FragmentConfirmPurchasesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentConfirmPurchasesBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)








        lifecycleScope.launch {

            sessionManager.getUserToken.first()?.let{



                    token =it






            }

        }



        args.let {

            binding.price.text = "${decimalFormater.format(it.totalprice.toLong()) } تومان"

            binding.orderCode.text = it.productid

            productId = it.productid

            ZarinPalPurchase.fakePurchase(it.totalprice.toLong(), "test") { transActionId ->


                orderSituation = requireContext().getString(R.string.purchase_is_ok)

                viewModel.deleteAllProducts()




                checkOutViewModel.confirmApi(

                    BodyConfirmPurchase(productId, token, transActionId)


                )




                lifecycleScope.launch {

                    delay(5000)

                    findNavController().navigate(R.id.action_confirmPurchasesFragment_to_basketShoppingFragment)


                }




                Log.e("BodyConfirmPurchase","${productId} ${token} ${transActionId} ")



            }


        }







        binding.situation.text = orderSituation

















    }








    }





