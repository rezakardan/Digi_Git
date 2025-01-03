package com.example.digikalax.ui.product_detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.digikalax.R
import com.example.digikalax.data.datastore.SessionManager
import com.example.digikalax.data.models.comment.BodyResponseSetNewComment
import com.example.digikalax.databinding.FragmentProductSetCommentBinding
import com.example.digikalax.utils.constants.Constants
import com.example.digikalax.utils.network.NetworkChecker
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.viewmodel.product_detail.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProductSetCommentFragment : Fragment() {

    lateinit var binding: FragmentProductSetCommentBinding


    private val args: ProductSetCommentFragmentArgs by navArgs()



    var userName=""


    var productId = "0"

    private val viewModel by viewModels<ProductDetailViewModel>()
    var star = 1


    var token=""

    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var isNetworkChecker: NetworkChecker

    var isNetwork=false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductSetCommentBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {



            isNetworkChecker.checkNetwork().collect{

                isNetwork=it



            }
        }
        binding.back.setOnClickListener {


            findNavController().popBackStack()


        }


        args.let {

            binding.txtProductName.text = it.productname

            binding.imgSabteNazar.load(it.imageurl)

            Log.e("111", it.imageurl)


            productId = it.productid

            token=it.token


        }







lifecycleScope.launch {
        sessionManager.getUserName.first()?.let {

            if (it.isEmpty()){


               userName="کاربر مهمان"


            }else{

                userName=it

            }



        }
        }



        binding.btnComment.setOnClickListener {


            val title = binding.edttitle.text.toString()

            val comment = binding.edttitle3.text.toString()



            if (isNetwork) {

                if (comment.isNotEmpty() && title.isNotEmpty()) {
                    viewModel.setNewComment(
                        BodyResponseSetNewComment(
                            token = token,
                            productId = productId,
                            star = star,
                            title = title,
                            description = comment,
                            userName = userName


                        )
                    )


                    //  findNavController().popBackStack()


                } else {


                    Toast.makeText(context, " لطفا همه فیلدها را پر کنید", Toast.LENGTH_SHORT)
                        .show()

                    // Toast.makeText(context, " عنوان کامنت را  بنویسید", Toast.LENGTH_SHORT).show()


                    //  Toast.makeText(context, " امتیاز بدهید ", Toast.LENGTH_SHORT).show()


                }


            }

        }
        loadSetNewComment()


        binding.sekkBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {


                when (p1 + 1) {


                    1 -> {
                        binding.txtSekkBar.text = requireContext().getString(R.string.very_bad)


                        star = 1
                    }

                    2 -> {
                        binding.txtSekkBar.text = requireContext().getString(R.string.bad)
                        star = 2
                    }

                    3 -> {
                        binding.txtSekkBar.text = requireContext().getString(R.string.normal)
                        star = 3

                    }

                    4 -> {
                        binding.txtSekkBar.text = requireContext().getString(R.string.good)
                        star = 4

                    }


                    else -> {
                        binding.txtSekkBar.text = requireContext().getString(R.string.very_good)
                        star = 5

                    }


                }


            }

            override fun onStartTrackingTouch(p0: SeekBar?) {


            }

            override fun onStopTrackingTouch(p0: SeekBar?) {


            }


        })

    }


    private fun loadSetNewComment() {
        viewModel.productNewCommentLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {


                is NetworkRequest.Loading -> {

                }


                is NetworkRequest.Success -> {

                   // if (response.error.equals("کامنت با موفقیت ثبت شد")) {


                        findNavController().popBackStack(R.id.productDetailFragment, false)
                   // }

                }

                is NetworkRequest.Error -> {
                    Toast.makeText(
                        context,
                        response.error.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }
        }
    }
}


