package com.example.digikalax.ui.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digikalax.R
import com.example.digikalax.data.datastore.SessionManager
import com.example.digikalax.data.db.basket.CartEntity
import com.example.digikalax.data.db.basket.CartStatus
import com.example.digikalax.databinding.FragmentBasketNextShoppingBinding
import com.example.digikalax.databinding.ItemNextShoppingBasketBinding
import com.example.digikalax.ui.basket.adapters.NextShoppingAdapter
import com.example.digikalax.viewmodel.basket.BasketViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class BasketNextShoppingFragment : Fragment() {


    lateinit var binding: FragmentBasketNextShoppingBinding


    private val viewModel by viewModels<BasketViewModel>()


    @Inject
    lateinit var adapter: NextShoppingAdapter


    @Inject
    lateinit var sessionManager: SessionManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasketNextShoppingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


binding.imgBack.setOnClickListener {

    findNavController().popBackStack()
}


        lifecycleScope.launch {



            delay(2000)


            viewModel.nextCartItem.collect { data ->


                if (data.isNotEmpty()) {


                    binding.constEmpty.visibility = View.GONE

                    binding.recNextShopping.visibility = View.VISIBLE
                    adapter.setData(data)

                    binding.recNextShopping.adapter = adapter
                    binding.recNextShopping.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


                } else {

                    binding.constEmpty.visibility = View.VISIBLE



                    binding.recNextShopping.visibility = View.GONE


                }


            }


        }





        adapter.setOnItemClickListener { cartEntity, string ->


            when (string) {

                "deleteFromNextCart" -> {








                    viewModel.deleteProductFromDatabase(
                        CartEntity(
                            cartEntity.itemId,
                            cartEntity.name,
                            cartEntity.seller,
                            cartEntity.price,
                            cartEntity.discountPercent,
                            cartEntity.image,
                            cartEntity.count,
                            CartStatus.NEXT_CART
                        )
                    )


                }


                "changeStatus" -> {


                    lifecycleScope.launch {


                        delay(500)

                        viewModel.changeStatus(CartStatus.CURRENT_CART, cartEntity.name)


                    }

                }


            }


        }



        lifecycleScope.launch {

            var token = sessionManager.getUserToken.first()





            if (token != null) {


                binding.entryOrRegisterUserConstLay.visibility = View.GONE

            } else {


                binding.entryOrRegisterUserConstLay.visibility = View.VISIBLE
            }

        }

        binding.entryOrRegisterUserConstLay.setOnClickListener {

            findNavController().navigate(R.id.myDigiKalaFragment)


        }


    }


}