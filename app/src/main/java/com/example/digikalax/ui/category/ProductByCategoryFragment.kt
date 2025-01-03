package com.example.digikalax.ui.category

import android.annotation.SuppressLint
import android.os.Bundle
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
import com.example.digikalax.R
import com.example.digikalax.databinding.FragmentProductByCategoryBinding
import com.example.digikalax.ui.category.adapters.InterNalAdapter
import com.example.digikalax.ui.product_detail.adapters.SimilarProductsAdapter
import com.example.digikalax.utils.extentions.singleObserve
import com.example.digikalax.utils.network.NetworkChecker
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.viewmodel.categories.CategoryViewModel
import com.example.digikalax.viewmodel.product_detail.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProductByCategoryFragment : Fragment() {

    lateinit var binding: FragmentProductByCategoryBinding


    private val viewModel by viewModels<CategoryViewModel>()


    private val args: ProductByCategoryFragmentArgs by navArgs()


    @Inject
    lateinit var similarProductsAdapter: SimilarProductsAdapter


    @Inject
    lateinit var networkChecker: NetworkChecker

    private var isNetwork=false
    @Inject
    lateinit var interNalAdapter: InterNalAdapter





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductByCategoryBinding.inflate(inflater, container, false)

        return binding.root
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {


            findNavController().popBackStack()

        }




        similarProductsAdapter.setOnItemClickListener {


            val directions =
                ProductByCategoryFragmentDirections.actionProductByCategoryFragmentToProductDetailFragment(
                    it
                )

            findNavController().navigate(directions)





        }







        interNalAdapter.setOnItemClickListener {


            val direction =
                ProductByCategoryFragmentDirections.actionProductByCategoryFragmentToProductDetailFragment(
                    it
                )

            findNavController().navigate(direction)

        }


        lifecycleScope.launch {



            networkChecker.checkNetwork().collect{





                    isNetwork=it






            }
        }




        args.let {

            lifecycleScope.launch {




                if (isNetwork){

                    viewModel.getProductBySubCategory(it.id)


                }




            }


        }

        interNalAdapter.setOnItemClickListener {


            val directions =
                ProductByCategoryFragmentDirections.actionProductByCategoryFragmentToProductDetailFragment(
                    it
                )

            findNavController().navigate(directions)

        }




        loadProductBySubCategory()


    }


    private fun loadProductBySubCategory() {
        viewModel.getProductBySubCategoryLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {

                is NetworkRequest.Loading -> {

                    binding.progressBar.visibility = View.VISIBLE

                    binding.subCategoryRecy.visibility = View.GONE

                }


                is NetworkRequest.Success -> {
                    binding.progressBar.visibility = View.GONE

                    binding.subCategoryRecy.visibility = View.VISIBLE

                    response.data?.data?.let { data ->





                        interNalAdapter.setData(data)

                        binding.subCategoryRecy.adapter = interNalAdapter

                        binding.subCategoryRecy.layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                        )


                    }
                }


                is NetworkRequest.Error -> {

                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }


            }


        }
    }


}