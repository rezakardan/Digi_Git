package com.example.digikalax.ui.product_detail

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
import com.example.digikalax.data.db.favorite.FavoriteDao
import com.example.digikalax.data.db.favorite.FavoriteEntity
import com.example.digikalax.databinding.FragmentFavoriteProductBinding
import com.example.digikalax.ui.home.adapters.FavoriteAdapter
import com.example.digikalax.ui.product_detail.adapters.MyFavoriteAdapter
import com.example.digikalax.utils.network.NetworkChecker
import com.example.digikalax.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteProductFragment : Fragment() {

    lateinit var binding: FragmentFavoriteProductBinding


    private val viewModel by viewModels<FavoriteViewModel>()


    @Inject
    lateinit var favoriteAdapter: MyFavoriteAdapter


    @Inject
    lateinit var favoriteDao: FavoriteDao



    @Inject
    lateinit var networkChecker: NetworkChecker

    var isNetwork=false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





      binding .imgEmptyFav.setOnClickListener {


          findNavController().popBackStack()



      }




        lifecycleScope.launch {




networkChecker.checkNetwork().collect{

isNetwork=it
}

}






if (isNetwork) {
    viewModel.loadAllFavoriteItems()
}






        viewModel.loadAllFavoriteLiveData.observe(viewLifecycleOwner) {


            binding.countProduct.text = "  کالا   ${it.size.toString()}"





            if (it.isNotEmpty()) {

                binding.constCountProduct.visibility = View.VISIBLE

                binding.recyFav.visibility = View.VISIBLE


                binding.constEmptyFav.visibility = View.GONE


                favoriteAdapter.setData(it)


                binding.recyFav.adapter = favoriteAdapter

                binding.recyFav.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


            } else {


                binding.constCountProduct.visibility = View.GONE

                binding.recyFav.visibility = View.GONE


                binding.constEmptyFav.visibility = View.VISIBLE


            }


        }



        favoriteAdapter.setOnItemClickListener { favEntity, type ->


            when (type) {


                "navigate" -> {

                    val directions =
                        FavoriteProductFragmentDirections.actionFavoriteProductFragmentToProductDetailFragment(
                            favEntity.id
                        )

                    findNavController().navigate(directions)


                }


                "delete" -> {

                    lifecycleScope.launch {

                        lifecycleScope.launch {
                            viewModel.deleteFavoriteItems(
                                FavoriteEntity(
                                    favEntity.id,
                                    favEntity.discountPercent,
                                    favEntity.image,
                                    favEntity.name,
                                    favEntity.price,
                                    favEntity.seller,
                                    favEntity.star
                                )
                            )

                        }
                    }


                }


            }


        }


    }
}