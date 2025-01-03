package com.example.digikalax.ui.product_detail

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.digikalax.R
import com.example.digikalax.data.datastore.SessionManager
import com.example.digikalax.data.db.basket.CartEntity
import com.example.digikalax.data.db.basket.CartStatus
import com.example.digikalax.data.db.favorite.FavoriteEntity
import com.example.digikalax.data.models.basket.CartDetails
import com.example.digikalax.data.models.product_detail.ResponseGetProductById
import com.example.digikalax.data.models.product_detail.ResponseGetSimilarProducts
import com.example.digikalax.databinding.FragmentProductDetailBinding
import com.example.digikalax.ui.product_detail.adapters.ColorAdapter
import com.example.digikalax.ui.product_detail.adapters.NazaratAdapter
import com.example.digikalax.ui.product_detail.adapters.SimilarProductsAdapter
import com.example.digikalax.ui.product_detail.adapters.SliderDetailAdapter
import com.example.digikalax.utils.extentions.singleObserve
import com.example.digikalax.utils.network.NetworkChecker
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.viewmodel.FavoriteViewModel
import com.example.digikalax.viewmodel.basket.BasketViewModel
import com.example.digikalax.viewmodel.product_detail.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    lateinit var binding: FragmentProductDetailBinding

    var finalPrice: Long = 0L
    var discountPrice: Long = 0L

    var categoryId = ""


    var discountPercent: Int = 0
    var seller = ""
    var star = ""

    @Inject
    lateinit var colorAdapter: ColorAdapter


    @Inject
    lateinit var nazaratAdapter: NazaratAdapter

    private val viewModel by viewModels<ProductDetailViewModel>()


    val decimalFormatter by lazy { DecimalFormat("##,###.##") }


    private val favViewModel by viewModels<FavoriteViewModel>()


    var price: String = ""

    var discountPrices: Long = 0L

    var autoScrollIndex = 0

    var technical = ""

    var description = ""

    var imageUrl = emptyList<ResponseGetProductById.Data.ImageSlider>()


    var data = ""
    var productName = ""
    var productId = ""
    private var isFavorite = false


    var count = 0


    var isAddedToCart = true

var isExistInDatabase=false
    var priceList = emptyList<ResponseGetProductById.Data>()


    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var sliderAdapter: SliderDetailAdapter


    @Inject
    lateinit var networkChecker: NetworkChecker

    var isNetwork = false


    private val basketViewModel by viewModels<BasketViewModel>()

var colorAdded=false
    @Inject
    lateinit var similarAdapter: SimilarProductsAdapter
    private val args: ProductDetailFragmentArgs by navArgs()






    var totalPrice = 0L
    var totalDiscount = 0L
    var payAblePrice = 0L
    var totalCount = 0
    var countFromOneProduct = 0












    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.goToBasketPage.visibility=View.GONE


      //  binding.goToBasketPage.setOnClickListener {
//
//
//            findNavController().navigate(R.id.action_productDetailFragment_to_basketShoppingFragment)
//
//
//
//        }



binding.popUp.visibility=View.GONE


        lifecycleScope.launch {


            networkChecker.checkNetwork().collect {

                isNetwork = it


            }
        }


        args.let {


            if (isNetwork) {
                viewModel.getProductIdApi(it.id)
            }
            Log.e("3333", productId)


        }








        basketViewModel.getCurrentCartItems.observe(viewLifecycleOwner) { item ->


            item.forEach { items ->


                totalPrice += ((items.price!!).times(items.count!!))



                if (items.discountPercent!!.toInt() > 0) {


                    totalDiscount += (((items.price!!.toLong()) * (items.discountPercent!!.toLong())) / 100)


                } else {

                    totalDiscount += 0


                }

                totalCount += items.count!!
                countFromOneProduct++

            }

            payAblePrice = totalPrice - totalDiscount


        }



















        similarAdapter.setOnItemClickListener {


            val directions =
                ProductDetailFragmentDirections.actionProductDetailFragmentToSimilarProductFragment(
                    it
                )

            findNavController().navigate(directions)


        }







        binding.constLayLike.visibility = View.GONE




            binding.exit.setOnClickListener {
                findNavController().popBackStack()
            }








            binding.popUp.setOnClickListener {


                val popUpMenu = PopupMenu(context, it)

                val inflater = popUpMenu.menuInflater

                inflater.inflate(R.menu.menu_product_detail, popUpMenu.menu)


                popUpMenu.setOnMenuItemClickListener { item ->


                    when (item.itemId) {


                        R.id.priceChartTxt -> {


                            val direction =
                                ProductDetailFragmentDirections.actionProductDetailFragmentToProductPriceChartFragment(
                                    priceList.toTypedArray()
                                )


                            findNavController().navigate(direction)





                            true
                        }


                        else -> {


                            true
                        }

                    }


                }



                popUpMenu.show()


            }


//        if (technical != null) {
//
//            binding.constLayTechnicalProduct.visibility = View.VISIBLE
//        } else {
//            binding.constLayTechnicalProduct.visibility = View.GONE
//        }


//        binding.constLayTechnicalProduct.setOnClickListener {
//
//
//            val directions =
//                ProductDetailFragmentDirections.actionProductDetailFragmentToTechnicalProductFragment(technical)
//
//
//            findNavController().navigate(directions)
//
//
//        }


            binding.constLaySabteNazar.setOnClickListener {


                lifecycleScope.launch {


                    sessionManager.getUserToken.first().let { token ->

                        Log.e("2223", token.toString())


                        if (!token.isNullOrEmpty()) {
                            val direction =
                                ProductDetailFragmentDirections.actionProductDetailFragmentToProductSetCommentFragment(
                                    productName,
                                    productId,
                                    imageUrl[0].image,
                                    token
                                )
                            findNavController().navigate(direction)
                        } else {
                            findNavController().navigate(R.id.action_productDetailFragment_to_myDigiKalaFragment4)
                        }
                    }


                }


            }



            binding.didgahCount.setOnClickListener {


                val directions =
                    ProductDetailFragmentDirections.actionProductDetailFragmentToAllProductsCommentsFragment(
                        productId
                    )

                findNavController().navigate(directions)

            }














            if (description.isNotEmpty()) {


                binding.constLayDescriptionlityProduct.visibility = View.VISIBLE


                binding.constLayDescriptionlityProduct.setOnClickListener {


                    val directions =
                        ProductDetailFragmentDirections.actionProductDetailFragmentToProductDescriptionFragment(
                            description
                        )

                    findNavController().navigate(directions)


                }


            } else {

                binding.constLayDescriptionlityProduct.visibility = View.GONE


            }





















            viewModel.productIdLiveData.observe(viewLifecycleOwner) { response ->


                when (response) {


                    is NetworkRequest.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE

                        binding.sliderRecycler.visibility = View.GONE
                        binding.recyNazarat.visibility = View.GONE

                        binding.recyColor.visibility = View.GONE
                    }


                    is NetworkRequest.Success -> {
                        binding.progressBar.visibility = View.GONE


                        binding.sliderRecycler.visibility = View.VISIBLE

                        binding.recyColor.visibility = View.VISIBLE


                        binding.recyNazarat.visibility = View.VISIBLE











                        response.data?.data?.let { data ->




                            clickOnFavImg(data)

                            clickOnButtonContinueProcess(data)










                            sliderAdapter.setData(data.imageSlider)

                            binding.sliderRecycler.adapter = sliderAdapter

                            binding.sliderRecycler.layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                            autoScrollPosition(data.imageSlider)







                            productName = data.name

                            productId = data.id

                            imageUrl = data.imageSlider

                            priceList = listOf(data)

                            price = data.price.toString()
                            discountPercent = data.discountPercent










                            seller = data.seller
                            star = data.star.toString()

                            if (data.comments.isNotEmpty()) {


                                nazaratAdapter.setData(data.comments)

                                binding.recyNazarat.adapter = nazaratAdapter

                                binding.recyNazarat.layoutManager =
                                    LinearLayoutManager(
                                        context,
                                        LinearLayoutManager.HORIZONTAL,
                                        false
                                    )

                                binding.didgahCount.text = "${data.commentCount.toString()} نفر "


                            } else {


                                binding.recyNazarat.visibility = View.GONE

                                binding.didgah.visibility = View.GONE

                                binding.didgahCount.visibility = View.GONE

                            }
















                            binding.percent.text = "${data.discountPercent.toString()} %"



                            binding.priceBeforeDiscount.apply {


                                text = "${decimalFormatter.format(data.price.toLong())}تومان"



                                paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                            }


                            val discount = ((data.price) * (data.discountPercent) / 100)
                            finalPrice = ((data.price) - discount)

                            binding.priceAfterDiscount.text =
                                "تومان${decimalFormatter.format(finalPrice)}"











                            binding.headerCategory.text = data.category

                            binding.headerName.text = data.name

                            binding.txt1.text = " ( ${data.starCount} )"


                            binding.txt4.text = data.commentCount.toString()


                            binding.txt6.text = data.viewCount.toString()

                            binding.starLike.text = data.agreeCount.toString()







                            colorAdapter.setData(data.colors)
                            binding.recyColor.adapter = colorAdapter

                            binding.recyColor.layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                            colorAdapter.setOnItemClickListener {

                                binding.color.text = it

                                colorAdded=true

                            }


                            //   binding.point.text = data.agreeCount.toString() + " %"


                            categoryId = data.categoryId


                            viewModel.getSimilarProductsApi(categoryId)


                            binding.productPrice.text =
                                " ${decimalFormatter.format(data.price)} تومان "






                            description = data.description






                            technical = data.technicalFeatures.toString()


                        }


                    }


                    is NetworkRequest.Error -> {

                        Toast.makeText(
                            requireContext(),
                            response.error.toString(),
                            Toast.LENGTH_SHORT
                        ).show()

                    }


                }


            }






        loadSimilarCategoryById()












    }


    private fun loadSimilarCategoryById() {


        viewModel.getSimilarProductsLiveData.observe(viewLifecycleOwner) { response ->

            when (response) {


                is NetworkRequest.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerFav.visibility = View.GONE
                }


                is NetworkRequest.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerFav.visibility = View.VISIBLE

                    response.data?.data?.let { data ->


                        similarAdapter.setData(data)

                        binding.recyclerFav.adapter = similarAdapter

                        binding.recyclerFav.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


                    }


                }


                is NetworkRequest.Error -> {
                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }


            }
        }


    }


    private fun autoScrollPosition(list: List<ResponseGetProductById.Data.ImageSlider>) {

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






    private fun clickOnFavImg(data: ResponseGetProductById.Data){


        lifecycleScope.launch {
        favViewModel.existInDatabase(data.id)

        }

        checkExistInDatabase()





binding.favProduct.setOnClickListener {
        if (isExistInDatabase){


            deleteItemProduct(data)





        }else{

            saveItemProduct(data)




        }



}




    }







    private fun saveItemProduct(data: ResponseGetProductById.Data) {


        favViewModel.saveFavoriteItems(
            FavoriteEntity(
                data.id,
                data.discountPercent,
                data.imageSlider[0].image,
                data.name,
                data.price.toString(),
                data.seller,
                data.star
            )
        )



        binding.favProduct.apply {

            setColorFilter(ContextCompat.getColor(context,R.color.red))

            setImageResource(R.drawable.ic_heart_circle_minus)




        }


    }

    private fun deleteItemProduct(data: ResponseGetProductById.Data) {


        favViewModel.deleteFavoriteItems(
            FavoriteEntity(
                data.id,
                data.discountPercent,
                data.imageSlider[0].image,
                data.name,
                data.price.toString(),
                data.seller,
                data.star
            )
        )



        binding.favProduct.apply {

            setColorFilter(ContextCompat.getColor(context,R.color.green))

            setImageResource(R.drawable.ic_heart_circle_plus)




        }


    }


private fun checkExistInDatabase(){



    favViewModel.isProductInDatabaseLiveData.observe(viewLifecycleOwner){

isExistInDatabase=it

        if (it){


            binding.favProduct.apply {

                setColorFilter(ContextCompat.getColor(context,R.color.red))

                setImageResource(R.drawable.ic_heart_circle_minus)




            }







        }else{

            binding.favProduct.apply {

                setColorFilter(ContextCompat.getColor(context,R.color.green))

                setImageResource(R.drawable.ic_heart_circle_plus)




            }


        }






    }





}














    private fun clickOnButtonContinueProcess(data: ResponseGetProductById.Data){


        lifecycleScope.launch {
            basketViewModel.isProductInBasketDb(data.id)

        }

        checkExistInBasketDatabase()









        binding.buttonContinueProcess.setOnClickListener {



            if (colorAdded){



            if (!isAddedToCart){



saveItemProductInBasket(data)



isAddedToCart=true
            }



        }else{

                Toast.makeText(requireContext(), "ابتدا یک رنگ برای محصول انتخاب کنید", Toast.LENGTH_SHORT).show()



            }

        }


    }






    private fun saveItemProductInBasket(data: ResponseGetProductById.Data) {


        basketViewModel.addToCart(
            CartEntity(
                data.id,
                data.name,
                data.seller,
                finalPrice,
                data.discountPercent,
                data.imageSlider[0].image,
                1,
                CartStatus.CURRENT_CART
            )
        )



        binding.buttonContinueProcess.apply {

            setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.blue))

                text=" به سبد خرید اضافه شده است"




        }


    }






    private fun checkExistInBasketDatabase(){



        basketViewModel.isProductInBasketDbLiveData.observe(viewLifecycleOwner){

            isAddedToCart=it

            if (it){


                binding.buttonContinueProcess.apply {

                    setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.blue))

                    text="به سبد خرید اضافه شده است"




                }







            }


            }






        }











    override fun onResume() {
        super.onResume()

        autoScrollIndex = 0
    }


}

