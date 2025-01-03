package com.example.digikalax.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digikalax.databinding.FragmentCategoryBinding
import com.example.digikalax.ui.category.adapters.BeautyAdapter
import com.example.digikalax.ui.category.adapters.BookAdapter
import com.example.digikalax.ui.category.adapters.DigitalAdapter
import com.example.digikalax.ui.category.adapters.FashionAdapter
import com.example.digikalax.ui.category.adapters.HomeAdapter
import com.example.digikalax.ui.category.adapters.MobileAdapter
import com.example.digikalax.ui.category.adapters.NativeAdapter
import com.example.digikalax.ui.category.adapters.ProductByCategoryAdapter
import com.example.digikalax.ui.category.adapters.SportAdapter
import com.example.digikalax.ui.category.adapters.SuperMarketAdapter
import com.example.digikalax.ui.category.adapters.ToolsAdapter
import com.example.digikalax.ui.category.adapters.ToyAdapter
import com.example.digikalax.utils.network.NetworkChecker
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.viewmodel.categories.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    lateinit var binding: FragmentCategoryBinding


    @Inject
    lateinit var toolsAdapter: ToolsAdapter

    @Inject
    lateinit var digitalAdapter: DigitalAdapter


    @Inject
    lateinit var fashionAdapter: FashionAdapter


    @Inject
    lateinit var mobileAdapter: MobileAdapter
    @Inject
    lateinit var beautyAdapter: BeautyAdapter

    @Inject
    lateinit var toyAdapter: ToyAdapter
    @Inject
    lateinit var nativeAdapter: NativeAdapter
    @Inject
    lateinit var bookAdapter: BookAdapter
    @Inject
    lateinit var homeAdapter: HomeAdapter
    @Inject
    lateinit var sportAdapter: SportAdapter
    @Inject
    lateinit var superMarketAdapter: SuperMarketAdapter

    @Inject
    lateinit var productByCategoryAdapter: ProductByCategoryAdapter




    @Inject
    lateinit var networkChecker: NetworkChecker

    private var isNetwork=false



    private val viewModel by viewModels<CategoryViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

lifecycleScope.launch {


    networkChecker.checkNetwork().collect{


        isNetwork=it

    }


}

        if (isNetwork){
            viewModel.callSubCategory()

        }


        toolsAdapter.setOnItemClickListener {



            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryFragment(it)

            findNavController().navigate(directions)


        }


        digitalAdapter.setOnItemClickListener {



            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryFragment(it)

            findNavController().navigate(directions)


        }



        fashionAdapter.setOnItemClickListener {



            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryFragment(it)

            findNavController().navigate(directions)


        }



        mobileAdapter.setOnItemClickListener {



            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryFragment(it)

            findNavController().navigate(directions)


        }





        beautyAdapter.setOnItemClickListener {



            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryFragment(it)

            findNavController().navigate(directions)


        }







        toyAdapter.setOnItemClickListener {



            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryFragment(it)

            findNavController().navigate(directions)


        }




        nativeAdapter.setOnItemClickListener {



            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryFragment(it)

            findNavController().navigate(directions)


        }





        bookAdapter.setOnItemClickListener {



            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryFragment(it)

            findNavController().navigate(directions)


        }





        homeAdapter.setOnItemClickListener {



            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryFragment(it)

            findNavController().navigate(directions)


        }




        sportAdapter.setOnItemClickListener {



            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryFragment(it)

            findNavController().navigate(directions)


        }




        superMarketAdapter.setOnItemClickListener {



            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryFragment(it)

            findNavController().navigate(directions)


        }





        binding.btnIndustrialToolsAndEquipment.setOnClickListener {

            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryNameFragment("industrial")

            findNavController().navigate(directions)





        }


        binding.btnDigitalGoods.setOnClickListener {




            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryNameFragment("digital")

            findNavController().navigate(directions)







        }
        binding.btnMobile.setOnClickListener {
            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryNameFragment("mobile")

            findNavController().navigate(directions)







        }

        binding.btnToysChildrenAndBabies.setOnClickListener {

            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryNameFragment("toys")

            findNavController().navigate(directions)





        }


        binding.btnSportsAndTravel.setOnClickListener {

            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryNameFragment("sports")

            findNavController().navigate(directions)





        }




        binding.btnHomeAndKitchen.setOnClickListener {
            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryNameFragment("home")

            findNavController().navigate(directions)






        }



        binding.btnBooksAndStationery.setOnClickListener {

            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryNameFragment("books")

            findNavController().navigate(directions)





        }



        binding.btnBeautyAndHealth.setOnClickListener {
            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryNameFragment("beauty")

            findNavController().navigate(directions)






        }



        binding.btnNativeAndLocalProducts.setOnClickListener {

            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryNameFragment("native")

            findNavController().navigate(directions)





        }


        binding.btnSupermarketProduct.setOnClickListener {

            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryNameFragment("supermarket")

            findNavController().navigate(directions)





        }


        binding.btnFashionAndClothing.setOnClickListener {

            val directions=CategoryFragmentDirections.actionCategoryFragmentToProductByCategoryNameFragment("fashion")

            findNavController().navigate(directions)





        }





        loadSubCategories()
        loadGetProductByCategory()
    }

    private fun loadSubCategories() {
        viewModel.subCategoryLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {

                is NetworkRequest.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyTools.visibility = View.GONE
                    binding.recyDigital.visibility = View.GONE

                    binding.recyMobile.visibility = View.GONE


                    binding.recyFashion.visibility = View.GONE

                    binding.recyFashion.visibility = View.GONE

                    binding.recyBeauty.visibility = View.GONE
                    binding.recyBook.visibility = View.GONE
                    binding.recyHome.visibility = View.GONE
                    binding.recyNative.visibility = View.GONE
                    binding.recySport.visibility = View.GONE
                    binding.recySuperMarkets.visibility = View.GONE
                    binding.recyToy.visibility = View.GONE


                binding.constTools.visibility=View.GONE

                    binding.consDigital.visibility=View.GONE

                    binding.consMobile.visibility=View.GONE

                    binding.consToy.visibility=View.GONE

                    binding.consSport.visibility=View.GONE

                    binding.consHome.visibility=View.GONE

                    binding.consBook.visibility=View.GONE

                    binding.consBeauty.visibility=View.GONE

                    binding.consNative.visibility=View.GONE

                    binding.consSuperMarkets.visibility=View.GONE

                    binding.constFashion.visibility=View.GONE



                }


                is NetworkRequest.Success -> {


                    response.data?.let { data ->


                        binding.progressBar.visibility = View.GONE
                        binding.recyTools.visibility = View.VISIBLE

                        binding.recyDigital.visibility = View.VISIBLE


                        binding.recyMobile.visibility = View.VISIBLE


                        binding.recyFashion.visibility = View.VISIBLE

                        binding.recyBeauty.visibility = View.VISIBLE
                        binding.recyBook.visibility = View.VISIBLE
                        binding.recyHome.visibility = View.VISIBLE
                        binding.recyNative.visibility = View.VISIBLE
                        binding.recySport.visibility = View.VISIBLE
                        binding.recySuperMarkets.visibility = View.VISIBLE
                        binding.recyToy.visibility = View.VISIBLE




                        binding.constTools.visibility=View.VISIBLE

                        binding.consDigital.visibility=View.VISIBLE

                        binding.consMobile.visibility=View.VISIBLE

                        binding.consToy.visibility=View.VISIBLE

                        binding.consSport.visibility=View.VISIBLE

                        binding.consHome.visibility=View.VISIBLE

                        binding.consBook.visibility=View.VISIBLE

                        binding.consBeauty.visibility=View.VISIBLE

                        binding.consNative.visibility=View.VISIBLE

                        binding.consSuperMarkets.visibility=View.VISIBLE

                        binding.constFashion.visibility=View.VISIBLE










                        toolsAdapter.setData(data.data.tool)
                        binding.recyTools.adapter = toolsAdapter
                        binding.recyTools.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)





                        digitalAdapter.setData(data.data.digital)
                        binding.recyDigital.adapter = digitalAdapter
                        binding.recyDigital.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)






                        fashionAdapter.setData(data.data.fashion)
                        binding.recyFashion.adapter = fashionAdapter
                        binding.recyFashion.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)





                        mobileAdapter.setData(data.data.mobile)
                        binding.recyMobile.adapter = mobileAdapter
                        binding.recyMobile.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


                        beautyAdapter.setData(data.data.beauty)
                        binding.recyBeauty.adapter = beautyAdapter
                        binding.recyBeauty.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)



                        bookAdapter.setData(data.data.book)
                        binding.recyBook.adapter = bookAdapter
                        binding.recyBook.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)




                        homeAdapter.setData(data.data.home)
                        binding.recyHome.adapter = homeAdapter
                        binding.recyHome.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)





                        nativeAdapter.setData(data.data.native)
                        binding.recyNative.adapter = nativeAdapter
                        binding.recyNative.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)





                        sportAdapter.setData(data.data.sport)
                        binding.recySport.adapter = sportAdapter
                        binding.recySport.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)





                        superMarketAdapter.setData(data.data.supermarket)
                        binding.recySuperMarkets.adapter = superMarketAdapter
                        binding.recySuperMarkets.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)





                        toyAdapter.setData(data.data.toy)
                        binding.recyToy.adapter = toyAdapter
                        binding.recyToy.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    }
                }


                is NetworkRequest.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }


            }


        }
    }





    private fun loadGetProductByCategory() {
        viewModel.getProductByCategoryLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {

                is NetworkRequest.Loading -> {






                }


                is NetworkRequest.Success -> {


                    response.data?.let { data ->


                        productByCategoryAdapter.setData(data.data)

binding.recyToy.adapter=productByCategoryAdapter

                        binding.recyToy.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)




                    }
                }


                is NetworkRequest.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }


            }


        }
    }


}