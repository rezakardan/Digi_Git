package com.example.digikalax.ui.checkout

import android.annotation.SuppressLint
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digikalax.R
import com.example.digikalax.data.datastore.SessionManager
import com.example.digikalax.data.db.basket.CartEntity
import com.example.digikalax.data.models.addresses.BodyItemSaveAddress
import com.example.digikalax.data.models.addresses.ResponseGetUserAddress
import com.example.digikalax.data.models.order.BodyResponseNewOrder
import com.example.digikalax.databinding.DialogChangeAddressBinding
import com.example.digikalax.databinding.FragmentCheckOutBinding
import com.example.digikalax.ui.addresses.AddressAdapter
import com.example.digikalax.ui.checkout.adapters.MarsoleAdapter
import com.example.digikalax.utils.network.NetworkChecker
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.viewmodel.addresses.AddressesViewModel
import com.example.digikalax.viewmodel.basket.BasketViewModel
import com.example.digikalax.viewmodel.checkout.CheckOutViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CheckOutFragment : Fragment() {

    lateinit var binding: FragmentCheckOutBinding


    private val viewModel by viewModels<AddressesViewModel>()


    var currentCartItems: List<CartEntity> = emptyList()

    private val checkOutViewModel by viewModels<CheckOutViewModel>()

    @Inject
    lateinit var sessionManager: SessionManager


    @Inject
    lateinit var marsoleAdapter: MarsoleAdapter


    private val basketViewModel by viewModels<BasketViewModel>()


    private val decimalFormater by lazy { DecimalFormat("##,###.##") }


    var address = ""
    var addressPhone = ""
    var addressName = ""
    var orderId = ""

    var token = ""
    var hazineSending = "0"


    var saveAddress: String? = null
    var savename: String? = null
    var savePostalCode: String? = null
    var savePhoneNumber: String? = null



    @Inject
    lateinit var networkChecker: NetworkChecker


    var isNetwork=false

    @Inject
    lateinit var addressesAdapter: AddressAdapter


    var totalPrice = 0L
    var totalDiscount = 0L
    var payAblePrice = 0L
    var totalCount = 0
    var countFromOneProduct = 0


    var finalPrice = 0L



    var priceSend=0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckOutBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            findNavController().navigate(R.id.basketShoppingFragment)
        }


        lifecycleScope.launch {


            networkChecker.checkNetwork().collect{



                isNetwork=it



            }




        }


        lifecycleScope.launch {

            sessionManager.getUserToken.first()?.let {



                token = it











                            viewModel.callGetUserAddresses(it)












            }
            Log.e("555", "token :${token}")

        }


































        binding.buttonContinueProcess.setOnClickListener {







            if (binding.address.text.isNotEmpty() && binding.userAddress.text.isNotEmpty()  ) {





                    checkOutViewModel.newOrderApi(

                        BodyResponseNewOrder(

                            orderAddress = address,
                            orderProducts = currentCartItems,
                            orderTotalDiscount = 0L,
                            orderTotalPrice = finalPrice,
                            orderUserPhone = addressPhone,
                            orderUserName = addressName,
                            token = token


                        )


                    )

                Log.e("BodyResponseNewOrder","${address} ${currentCartItems} ${0L} ${finalPrice} ${addressPhone} ${addressName} ${token}")

            } else {
                Toast.makeText(context, "لطفا مشخصات آدرس را پر کنید", Toast.LENGTH_SHORT).show()
            }
        }

















        loadNewOrder()

























        loadGetUserAddresses()





        if (address.isNotEmpty()) {






                checkOutViewModel.getShippingCostApi(address)


        }else{

            checkOutViewModel.getShippingCostApi("address")


        }


        loadGetShippingCost()




        basketViewModel.getCurrentCartItems.observe(viewLifecycleOwner) {item->








            item.forEach { items ->


                totalPrice += ((items.price!!).times(items.count!!))



                if (items.discountPercent!!.toInt() >0) {


                    totalDiscount += (((items.price!!.toLong()) * (items.discountPercent!!.toLong())) / 100)


                } else {

                    totalDiscount += 0


                }

                totalCount += items.count!!
                countFromOneProduct++

            }







            currentCartItems = item

            marsoleAdapter.setData(item)

            binding.recyMarsole.adapter = marsoleAdapter

            Log.e("1234", "it: ${item}")

            binding.recyMarsole.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)











            payAblePrice = totalPrice - totalDiscount












            binding.countProduct.visibility=View.GONE



            finalPrice = payAblePrice

            binding.finalPriceBeforeDiscount.text =
                "        ${decimalFormater.format(((payAblePrice)))}  تومان "




            binding.priceAllroductsAfterDiscount.text= "        ${decimalFormater.format(((totalPrice)))} تومان"



            binding.priceDiscountAllroducts.text="        ${decimalFormater.format(((totalDiscount)))}تومان "









        }







    }


    private fun loadNewOrder() {
        checkOutViewModel.newOrderLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {


                is NetworkRequest.Loading -> {
binding.progressBar.visibility=View.VISIBLE
                }


                is NetworkRequest.Success -> {
                    binding.progressBar.visibility=View.GONE
                    response.data?.data?.let { data ->


                        val directions =
                            CheckOutFragmentDirections.actionCheckOutFragmentToConfirmPurchasesFragment(
                                priceSend.toString(),
                                data
                            )

                        findNavController().navigate(directions)




                        orderId = data
                        Log.e("222", "orderId :${orderId}")

                    }


                }


                is NetworkRequest.Error -> {
                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }
            }


        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadGetShippingCost() {

        checkOutViewModel.getShippingCostLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {


                is NetworkRequest.Loading -> {

                }


                is NetworkRequest.Success -> {

                    response.data?.data?.let { data ->


                        binding.descOfProducts.text = "        ${decimalFormater.format(((data.toLong())))}تومان "



                       // if (data != 0) {

                             priceSend=(finalPrice + data)



                            binding.txtPriceShopping.text = "        ${decimalFormater.format(((priceSend.toLong())))}تومان "






                            binding.finalPrice.text =   "        ${decimalFormater.format(((priceSend.toLong())))}تومان "








//                        } else {
//
//                            binding.txtPriceShopping.text =   "        ${decimalFormater.format(((finalPrice)))}تومان "
//
//
//                            binding.finalPrice.text = "        ${decimalFormater.format(((finalPrice)))}تومان "
//                        }


                    }


                }


                is NetworkRequest.Error -> {
                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun loadGetUserAddresses() {
        viewModel.getUserAddressessLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {


                is NetworkRequest.Loading -> {
binding.progressBar.visibility=View.VISIBLE




                }


                is NetworkRequest.Success -> {

                    binding.progressBar.visibility=View.GONE

                    response.data?.data?.let { data ->


                        if (data.isEmpty().not()) {

                            data[0].let {


                                address = it.address.toString()


                                addressName = it.name.toString()

                                addressPhone = it.phone.toString()

                                binding.address.text=it.address.toString()

                                binding.phoneNumber.text=it.phone

                                        binding.postalCodes.text=it.postalCode


                                binding.userAddress.text=it.name
                            }

                            if (data.size > 1) {


                                binding.constLayGoToAddressPage.setOnClickListener {


                                    changeAddressDialog(data)


                                }


                            }
                        }


                    }

                }


                is NetworkRequest.Error -> {
                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }


            }


        }
    }


    private fun changeAddressDialog(data: List<ResponseGetUserAddress.Data>) {


        val dialogBinding = DialogChangeAddressBinding.inflate(layoutInflater)

        // ایجاد AlertDialog
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)



            .create() //



        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)




        // ایجاد دیالوگ

        // تنظیم RecyclerView
        addressesAdapter.setData(data)
        dialogBinding.recyclerAddress.adapter = addressesAdapter
        dialogBinding.recyclerAddress.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        // مدیریت کلیک بر روی آیتم‌های RecyclerView
        addressesAdapter.setOnItemClickListener {
            binding.address.text = it.address.toString()


            binding.postalCodes.text = it.postalCode.toString()

            binding.phoneNumber.text = it.phone.toString()

            binding.userAddress.text = it.name.toString()
            dialog.dismiss() // استفاده از dismiss برای بستن دیالوگ
        }

        // نمایش دیالوگ
        dialog.show()
    }
}