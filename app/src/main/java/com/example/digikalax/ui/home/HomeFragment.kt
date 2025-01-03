package com.example.digikalax.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import coil.load
import com.example.digikalax.R
import com.example.digikalax.data.models.home.ResponsSuperMarketProducts
import com.example.digikalax.data.models.home.ResponseGetAmazingProducts
import com.example.digikalax.databinding.FragmentHomeBinding
import com.example.digikalax.data.models.home.ResponseSliders
import com.example.digikalax.ui.home.adapters.AmazingAdapter
import com.example.digikalax.ui.home.adapters.BannersAdapter
import com.example.digikalax.ui.home.adapters.BestSellerAdapter
import com.example.digikalax.ui.home.adapters.CategoryAdapter
import com.example.digikalax.ui.home.adapters.FavoriteAdapter
import com.example.digikalax.ui.home.adapters.MostDiscountedAdapter
import com.example.digikalax.ui.home.adapters.MostVisitedAdapter
import com.example.digikalax.ui.home.adapters.SliderAdapter
import com.example.digikalax.ui.home.adapters.SuperMarketAdapter
import com.example.digikalax.utils.constants.Constants.AUCTION_URL
import com.example.digikalax.utils.constants.Constants.DIGIJET_URL
import com.example.digikalax.utils.constants.Constants.DIGIPAY_URL
import com.example.digikalax.utils.constants.Constants.DIGIPLUS_URL
import com.example.digikalax.utils.constants.Constants.GIFT_CARD_URL
import com.example.digikalax.utils.constants.Constants.MORE_URL
import com.example.digikalax.utils.constants.Constants.PINDO_URL
import com.example.digikalax.utils.constants.Constants.SHOPPING_URL
import com.example.digikalax.utils.network.NetworkChecker
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.viewmodel.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding


    private val viewModel by viewModels<HomeViewModel>()


    @Inject
    lateinit var sliderAdapter: SliderAdapter


    @Inject
    lateinit var amazingAdapter: AmazingAdapter


    @Inject
    lateinit var bannersAdapter: BannersAdapter

    @Inject
    lateinit var superMarketAdapter: SuperMarketAdapter


    @Inject
    lateinit var categoryAdapter: CategoryAdapter


    @Inject
    lateinit var bestSellerAdapter: BestSellerAdapter

    @Inject
    lateinit var mostVisitedAdapter: MostVisitedAdapter

    @Inject
    lateinit var favoriteAdapter:FavoriteAdapter



    @Inject
    lateinit var mostDiscountAdapter:MostDiscountedAdapter


    @Inject
    lateinit var isNetworkChecker:NetworkChecker

    var isNetwork=false




    var autoScrollIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        lifecycleScope.launch {



isNetworkChecker.checkNetwork().collect{

isNetwork=it



}
}



        sliderAdapter.setOnItemClickListener {


            val intent=Intent(Intent.ACTION_VIEW, Uri.parse(it))

startActivity(intent)

        }



        bannersAdapter.setOnItemClickListener {


            val intent=Intent(Intent.ACTION_VIEW, Uri.parse(it))

            startActivity(intent)

        }


        categoryAdapter.setOnItemClickListener {


            val directions=HomeFragmentDirections.actionHomeFragmentToProductByCategoryFragment(it)
            findNavController().navigate(directions)

        }


        bestSellerAdapter.setOnItemClickListener {


            val directions=HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(it)
            findNavController().navigate(directions)

        }


        mostVisitedAdapter.setOnItemClickListener {


            val directions=HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(it)
            findNavController().navigate(directions)

        }


        favoriteAdapter.setOnItemClickListener {


            val directions=HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(it)
            findNavController().navigate(directions)

        }


        mostDiscountAdapter.setOnItemClickListener {


            val directions=HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(it)
            findNavController().navigate(directions)

        }


if (isNetwork){








        viewModel.callSliderApi()
        viewModel.callAmazingProduct()

        viewModel.callSuperMarketProduct()

        viewModel.callGetBanners()


        viewModel.callCategory()

        viewModel.callCenterBanners()

        viewModel.callBestSeller()

        viewModel.callMostVisitedProducts()

        viewModel.callFavoriteProducts()

viewModel.callMostDiscountedProducts()


}

        loadSliderApi()


        loadAmazingProducts()

        loadSuperMarketProducts()

        loadBannerAdapter()

        loadCategories()

        loadCenterBanners()


        loadBestSeller()

        loadMostVisitedProducts()

        loadFavoriteProducts()


        loadMostDiscountAdapter()

        binding.const1.setOnClickListener {


            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(DIGIJET_URL))

            startActivity(intent)


        }






        binding.const2.setOnClickListener {


            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(AUCTION_URL))

            startActivity(intent)


        }


        binding.const3.setOnClickListener {


            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(DIGIPAY_URL))

            startActivity(intent)


        }


        binding.const4.setOnClickListener {


            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(PINDO_URL))

            startActivity(intent)


        }

        binding.const5.setOnClickListener {


            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(SHOPPING_URL))

            startActivity(intent)


        }



        binding.const6.setOnClickListener {


            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(GIFT_CARD_URL))

            startActivity(intent)


        }


        binding.const7.setOnClickListener {


            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(DIGIPLUS_URL))

            startActivity(intent)


        }

        binding.const8.setOnClickListener {


            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(MORE_URL))

            startActivity(intent)


        }


       // findNavController().popBackStack(R.id.splashFragment, true)


    }




    private fun loadSliderApi() {


        viewModel.getSliderLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {

                is NetworkRequest.Loading -> {

                    binding.consttt.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE

                }

                is NetworkRequest.Success -> {



                    binding.progressBar.visibility = View.GONE
                    binding.consttt.visibility = View.VISIBLE



                    response.data?.let { data ->



                        sliderAdapter.setData(data.data)

                        binding.sliderRecycler.adapter = sliderAdapter

                        binding.sliderRecycler.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                        autoScrollPosition(data.data)
                    }
                }


                is NetworkRequest.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }


            }


        }


    }


    private fun autoScrollPosition(list: List<ResponseSliders.Data>) {

        lifecycleScope.launch {

            repeat(10000) {

                delay(6000L)

                if (autoScrollIndex < list.size) {

                    autoScrollIndex += 1


                } else {
                    autoScrollIndex = 0
                }

                binding.sliderRecycler.smoothScrollToPosition(autoScrollIndex)


            }


        }


    }

    private fun loadAmazingProducts() {
        viewModel.amazingProductLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {

                is NetworkRequest.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE

                    binding.recyclerAmazing.visibility = View.GONE

                }


                is NetworkRequest.Success -> {

                    binding.progressBar.visibility = View.GONE
                    binding.recyclerAmazing.visibility = View.VISIBLE

                    response.data?.let { data ->






initAmazingRecycler(data.data )






                    }
                }


                is NetworkRequest.Error -> {
                    binding.progressBar.visibility = View.GONE

                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }


            }


        }
    }



    private fun initAmazingRecycler(data:List<ResponseGetAmazingProducts.Data>){




        amazingAdapter.setData(data)

        binding.recyclerAmazing.adapter = amazingAdapter

        binding.recyclerAmazing.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)

        amazingAdapter.setOnItemClickListener {




val directions=HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(it.id)

findNavController().navigate(directions)

        }


    }


    private fun loadSuperMarketProducts() {
        viewModel.superMarketProductLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {


                is NetworkRequest.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE

                    binding.recyclerSuperMarket.visibility = View.GONE
                }


                is NetworkRequest.Success -> {

                    binding.progressBar.visibility = View.GONE
                    binding.recyclerSuperMarket.visibility = View.VISIBLE

                    response.data?.let { data ->




                        initSuperMarkerRecycler(data.data)


                    }

                }


                is NetworkRequest.Error -> {
                    binding.progressBar.visibility = View.GONE

                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }


            }


        }
    }



    private fun initSuperMarkerRecycler(data:List<ResponsSuperMarketProducts.Data>){




        superMarketAdapter.setData(data)

        binding.recyclerSuperMarket.adapter = superMarketAdapter

        binding.recyclerSuperMarket.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)


        superMarketAdapter.setOnItemClickListener {


            val directions=HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(it.id)

            findNavController().navigate(directions)




        }


    }


    private fun loadBannerAdapter() {
        viewModel.getBannersLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {


                is NetworkRequest.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerBanners.visibility = View.GONE
                }


                is NetworkRequest.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerBanners.visibility = View.VISIBLE
                    response.data?.let { data ->



                        bannersAdapter.setData(data.data)

                        binding.recyclerBanners.adapter = bannersAdapter

                        binding.recyclerBanners.layoutManager =
                            GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)


                    }
                }


                is NetworkRequest.Error -> {
                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()

                }


            }


        }
    }


    private fun loadCategories() {
        viewModel.categoryLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {


                is NetworkRequest.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE

                    binding.recyclerCategory.visibility = View.GONE

                }


                is NetworkRequest.Success -> {

                    binding.progressBar.visibility = View.GONE

                    binding.recyclerCategory.visibility = View.VISIBLE
                    response.data?.let { data ->



                        categoryAdapter.setData(data.data)
                        binding.recyclerCategory.adapter = categoryAdapter
                        binding.recyclerCategory.layoutManager =
                            StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL)


                    }
                }


                is NetworkRequest.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }


            }


        }
    }


    private fun loadCenterBanners() {
        viewModel.centerBannerLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {

                is NetworkRequest.Loading -> {


                    binding.progressBar.visibility = View.VISIBLE


                }


                is NetworkRequest.Success -> {

                    binding.progressBar.visibility = View.GONE
                    response.data?.let { data ->







                        binding.imgCenterBanners.load(data.data[0].image)

                        binding.imgCenterBanners2.load(data.data[1].image)


                        binding.imgCenterBanners3.load(data.data[2].image)

                    }

                }


                is NetworkRequest.Error -> {
                    binding.progressBar.visibility = View.GONE

                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }

            }


        }
    }

    private fun loadBestSeller() {
        viewModel.bestSellerLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {


                is NetworkRequest.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerBestSeller.visibility = View.GONE

                }


                is NetworkRequest.Success -> {

                    binding.progressBar.visibility = View.GONE
                    binding.recyclerBestSeller.visibility = View.VISIBLE
                    response.data?.let { data ->





                        bestSellerAdapter.setData(data.data)

                        binding.recyclerBestSeller.adapter = bestSellerAdapter

                        binding.recyclerBestSeller.layoutManager =
                            GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)

                    }

                }


                is NetworkRequest.Error -> {

                    binding.progressBar.visibility = View.GONE

                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun loadMostVisitedProducts() {
        viewModel.mostVisitedProductLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {


                is NetworkRequest.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerMostVisited.visibility = View.GONE

                }


                is NetworkRequest.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerMostVisited.visibility = View.VISIBLE

                    response.data?.let { data ->





                        mostVisitedAdapter.setData(data.data)

                        binding.recyclerMostVisited.adapter = mostVisitedAdapter

                        binding.recyclerMostVisited.layoutManager =
                            GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)

                    }

                }


                is NetworkRequest.Error -> {

                    binding.progressBar.visibility = View.GONE

                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

    }



    private fun loadFavoriteProducts() {
viewModel.favoriteProductLiveData.observe(viewLifecycleOwner){response->


    when(response){

        is NetworkRequest.Loading->{
            binding.progressBar.visibility=View.VISIBLE
            binding.recyclerFav.visibility=View.GONE

        }


        is NetworkRequest.Success->{


            binding.progressBar.visibility=View.GONE

            binding.recyclerFav.visibility=View.VISIBLE
            response.data?.let { data->




                favoriteAdapter.setData(data.data)
                binding.recyclerFav.adapter=favoriteAdapter
                binding.recyclerFav.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)


            }

        }


        is NetworkRequest.Error->{
            binding.progressBar.visibility=View.GONE

            Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
        }







    }





}    }



    private fun loadMostDiscountAdapter() {
        viewModel.mostDicountedProductLiveData.observe(viewLifecycleOwner){response->



            when(response){



                is NetworkRequest.Loading->{
                    binding.recyclerMostDiscount.visibility=View.GONE
                    binding.progressBar.visibility=View.VISIBLE


                }



                is NetworkRequest.Success->{

                    binding.recyclerMostDiscount.visibility=View.VISIBLE
                    binding.progressBar.visibility=View.GONE
                    response.data?.let { data->



                        mostDiscountAdapter.setData(data.data)

                        binding.recyclerMostDiscount.adapter=mostDiscountAdapter
                        binding.recyclerMostDiscount.layoutManager=GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL,false)




                    }
                }


                is NetworkRequest.Error->{
                    binding.recyclerMostDiscount.visibility=View.VISIBLE
                }




            }
        }
    }
}