package com.example.digikalax.ui.product_detail

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digikalax.databinding.FragmentAllProductsCommentsBinding
import com.example.digikalax.ui.product_detail.adapters.AllCommentsProductAdapter
import com.example.digikalax.utils.network.NetworkChecker
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.viewmodel.allcomments.AllCommentsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class AllProductsCommentsFragment : Fragment() {


    lateinit var binding: FragmentAllProductsCommentsBinding

    private val args: AllProductsCommentsFragmentArgs by navArgs()



    @Inject
    lateinit var allCommentsProductAdapter: AllCommentsProductAdapter




    private val viewModel by viewModels<AllCommentsViewModel>()

    @Inject
    lateinit var isNetworkChecker: NetworkChecker

    var isNetwork = false




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllProductsCommentsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launch {


            isNetworkChecker.checkNetwork().collect {

                isNetwork = it


            }
        }








        args.let {


            if (isNetwork) {
                viewModel.productsGetAllComment(it.productidd)

            }
        }



        binding.back.setOnClickListener {


            findNavController().popBackStack()


        }








        loadAllComments()





    }

    private fun loadAllComments() {


        viewModel.productsGetAllCommentLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {


                is NetworkRequest.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                    binding.recyNazarat.visibility = View.GONE

                }


                is NetworkRequest.Success -> {


                    response.data?.data?.let { data ->


                        if (data.isNotEmpty()) {

                            binding.constEmptyLay.visibility = View.GONE

                            binding.loading.visibility = View.GONE

                            binding.recyNazarat.visibility = View.VISIBLE
                            allCommentsProductAdapter.setData(data)
                            binding.recyNazarat.adapter = allCommentsProductAdapter

                            binding.recyNazarat.layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


                            Log.e("adapter","${allCommentsProductAdapter.setData(data)}")





                        } else {


                            binding.constEmptyLay.visibility = View.VISIBLE



                            binding.recyNazarat.visibility = View.GONE


                        }


                    }


                }


                is NetworkRequest.Error -> {
                    binding.loading.visibility = View.GONE

                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }


            }


        }


    }
}


