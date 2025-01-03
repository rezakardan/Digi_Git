package com.example.digikalax.ui.basket

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digikalax.R
import com.example.digikalax.data.datastore.SessionManager
import com.example.digikalax.data.db.basket.CartEntity
import com.example.digikalax.data.db.basket.CartStatus
import com.example.digikalax.databinding.FragmentBasketShoppingBinding
import com.example.digikalax.ui.basket.adapters.CurrnentShoppingAdapter
import com.example.digikalax.ui.basket.adapters.SugestionAdapter
import com.example.digikalax.utils.network.NetworkChecker
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.viewmodel.addresses.AddressesViewModel
import com.example.digikalax.viewmodel.basket.BasketViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import javax.inject.Inject


@AndroidEntryPoint
class BasketShoppingFragment : Fragment() {

    lateinit var binding: FragmentBasketShoppingBinding


    @Inject
    lateinit var sessionManager: SessionManager


    @Inject
    lateinit var sugestionAdapter: SugestionAdapter


    private val decimalFormater by lazy { DecimalFormat("##,###.##") }

    @Inject
    lateinit var currentShoppingAdapter: CurrnentShoppingAdapter


    private val viewModel by viewModels<BasketViewModel>()

    var price = 0L
    var totalPrice = 0L


    var discount = 0L
    var totalDiscount = 0L



    var payAblePrice = 0L



    var count = 0
    var totalCount = 0



    var countFromOneProduct = 0


    @Inject
    lateinit var networkChecker: NetworkChecker

    var isNetwork = false
    var token = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasketShoppingBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        lifecycleScope.launch {


            networkChecker.checkNetwork().collect {


                isNetwork = it


            }


        }


//binding.txtGoToNextList.setOnClickListener {
//
//
//    findNavController().navigate(R.id.action_basketShoppingFragment_to_basketNextShoppingFragment)
//
//
//
//}


        sugestionAdapter.setOnItemClickListener { data, type ->


            when (type) {


                "detail" -> {
//
//
//val directions=BasketFragmentDirections.actionBasketShoppingFragmentToProductDetailFragment(data.id)
//
//findNavController().navigate(directions)
//
                }


                "add" -> {


                    data.let {


                        lifecycleScope.launch {


                            viewModel.addToCart(

                                CartEntity(
                                    it.id,
                                    it.name,
                                    it.seller,
                                    it.price.toLong(),
                                    it.discountPercent,
                                    it.image,
                                    1,
                                    CartStatus.CURRENT_CART
                                )


                            )


                        }
                    }
                }
            }
        }





        lifecycleScope.launch {


            viewModel.getCurrentCartItems.observe(viewLifecycleOwner) {


                if (it.isNotEmpty()) {

binding.txtsugesstion.visibility = View.VISIBLE
                    binding.view.visibility = View.VISIBLE


                    binding.view1.visibility = View.VISIBLE

                    binding.view2.visibility = View.VISIBLE


                    binding.const1.visibility = View.VISIBLE

                    binding.const4.visibility = View.VISIBLE

                    binding.descOfProducts.visibility = View.VISIBLE
                    binding.txtShopping.visibility = View.VISIBLE
                    binding.buttonContinueProcess.visibility = View.VISIBLE

                    binding.totalPrice.visibility = View.VISIBLE
                    binding.constPrice.visibility = View.VISIBLE



                    binding.buttonContinueProcess.visibility = View.VISIBLE

                    binding.constLayContinueShopping.visibility = View.VISIBLE

                    binding.constLaySummaryProduct.visibility = View.VISIBLE

                    binding.constEmpty.visibility = View.GONE

                    binding.constOffersLay.visibility = View.GONE


                    binding.recyCurrentProducts.visibility = View.VISIBLE

                    binding.constLayContinueShopping.visibility = View.VISIBLE

                    currentShoppingAdapter.setData(it)
                    binding.recyCurrentProducts.adapter = currentShoppingAdapter
                    binding.recyCurrentProducts.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


                    binding.constLaySummaryProduct.visibility = View.VISIBLE







                    it.forEach { items ->










                        if (items.count==0){


                            price=0


                        }else{

                            price += ((items.price!!).times(items.count!!))


                        }



                        Log.e("totalPriceee","${price}").toString()

                        if (items.discountPercent!!.toInt() > 0) {


                            discount += (((items.price!!.toLong()) * (items.discountPercent!!.toLong())) / 100)


                        } else {

                            discount += 0


                        }


                        if (items.count==0){


                            count=0


                        }else{

                            count                   += items.count!!


                        }







                        countFromOneProduct      ++

                    }

                    payAblePrice = totalPrice - totalDiscount


                    totalCount=count
                    totalPrice=price
                    totalDiscount=discount


                    Log.e("payAblePrice","${payAblePrice}").toString()
















                //    binding.priceAllroductsAfterDiscount.text = payAblePrice.toString()


                 //   binding.priceDiscountAllroducts.text = "  تومان    ${totalDiscount}"



               //     binding.finalPriceBeforeDiscount.text =
                   //     "  تومان    ${decimalFormater.format(totalPrice)}"


                 //   binding.txtPriceShopping.text =
                  //      "  تومان    ${decimalFormater.format(totalPrice)}"


                } else {






                    binding.txtsugesstion.visibility = View.GONE



                    binding.const1.visibility = View.VISIBLE

                    binding.const4.visibility = View.VISIBLE

                    binding.descOfProducts.visibility = View.VISIBLE
                    binding.txtShopping.visibility = View.VISIBLE
                    binding.buttonContinueProcess.visibility = View.VISIBLE



                    binding.totalPrice.visibility = View.GONE
                    binding.constPrice.visibility = View.GONE
                    binding.buttonContinueProcess.visibility = View.GONE

                    binding.constLayContinueShopping.visibility = View.GONE


                    binding.constLaySummaryProduct.visibility = View.GONE


                    binding.constLayContinueShopping.visibility = View.GONE

                    binding.constEmpty.visibility = View.VISIBLE

                    binding.constOffersLay.visibility = View.VISIBLE


                    binding.recyCurrentProducts.visibility = View.GONE

                    binding.constLaySummaryProduct.visibility = View.GONE

                }


            }


        }





        lifecycleScope.launch {


            viewModel.getAllProductPriceCurrentCart.observe(viewLifecycleOwner){




                binding.finalPriceBeforeDiscount.text =
                    "  تومان    ${decimalFormater.format(it)}"


                binding.txtPriceShopping.text =
                    "  تومان    ${decimalFormater.format(it)}"


             //   totalPrice=it


                //Log.e("totalPrice","${totalPrice}").toString()

            }


        }




        lifecycleScope.launch {


            viewModel.getCurrentCartItemCount.observe(viewLifecycleOwner){

                binding.productCounters.text = it.toString()



                Log.e("tedad","${it}").toString()

                totalCount=it


                Log.e("totalCount","${totalCount}").toString()

            }


        }


        currentShoppingAdapter.setOnItemClickListener { cartEntity, type ->


            when (type) {


                "delete" -> {


                    viewModel.deleteProductFromDatabase(
                        CartEntity(
                            cartEntity.itemId,
                            cartEntity.name,
                            cartEntity.seller,
                            cartEntity.price,
                            cartEntity.discountPercent,
                            cartEntity.image,
                            cartEntity.count,
                            CartStatus.CURRENT_CART
                        )
                    )
                }


                "increase" -> {


                    viewModel.updateCounts((cartEntity.count)!!.plus(1), cartEntity.name)
                }


                "decrease" -> {


                    viewModel.updateCounts((cartEntity.count)!!.minus(1), cartEntity.name)


                }








                "changeStatus" -> {


                    lifecycleScope.launch {


                        viewModel.changeStatus(CartStatus.NEXT_CART, cartEntity.name)

                    }


                }







            }

            Log.e("count","${cartEntity.count.toString()}").toString()
        }

        if (isNetwork) {

            viewModel.callSugestion()
        }
        loadCallSugesstion()











        lifecycleScope.launch {

            sessionManager.getUserToken.first()?.let {

                token = it


            }





            if (token.isNotEmpty()) {


                binding.entryOrRegisterUserConstLay.visibility = View.GONE

            } else {


                binding.entryOrRegisterUserConstLay.visibility = View.VISIBLE
            }

        }

        binding.entryOrRegisterUserConstLay.setOnClickListener {

            findNavController().navigate(R.id.myDigiKalaFragment)


        }



        binding.buttonContinueProcess.setOnClickListener {


            if (token.isNotEmpty()) {

                findNavController().navigate(R.id.action_basketShoppingFragment_to_checkOutFragment)


            } else {


                findNavController().navigate(R.id.action_basketShoppingFragment_to_myDigiKalaFragment)


            }


        }


    }


    private fun loadCallSugesstion() {
        viewModel.sugestionLiveData.observe(viewLifecycleOwner) { response ->

            when (response) {

                is NetworkRequest.Loading -> {

                    binding.recySugestion.visibility = View.GONE

                    binding.progressBar.visibility = View.VISIBLE

                }


                is NetworkRequest.Success -> {


                    binding.progressBar.visibility = View.GONE

                    binding.recySugestion.visibility = View.VISIBLE
                    response.data?.let { data ->


                        sugestionAdapter.setData(data.data)
                        binding.recySugestion.adapter = sugestionAdapter
                        binding.recySugestion.layoutManager =
                            GridLayoutManager(context, 10, GridLayoutManager.HORIZONTAL, false)


                    }

                }


                is NetworkRequest.Error -> {


                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT)
                        .show()
                }


            }


        }
    }


}
