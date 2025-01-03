package com.example.digikalax.ui.product_detail

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
import com.example.digikalax.data.datastore.SessionManager
import com.example.digikalax.databinding.FragmentUserCommentsBinding
import com.example.digikalax.ui.product_detail.adapters.UserCommentsAdapter
import com.example.digikalax.utils.network.NetworkChecker
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.viewmodel.allcomments.AllCommentsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UserCommentsFragment : Fragment() {

lateinit var binding: FragmentUserCommentsBinding




private val viewModel by viewModels<AllCommentsViewModel>()

    @Inject
    lateinit var isNetworkChecker: NetworkChecker

    var isNetwork=false



    @Inject
lateinit var commentsAdapter: UserCommentsAdapter


    @Inject
    lateinit var sessionManager:SessionManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentUserCommentsBinding.inflate(inflater,container,false)
        return binding.root
    }


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


lifecycleScope.launch {




        sessionManager.getUserToken.first()?.let {



            if (isNetwork) {

                viewModel.getUserComments(it)

            }
        }

        }



      loadUserComments()

    }

    private fun loadUserComments() {
        viewModel.getUserCommentsLiveData.observe(viewLifecycleOwner){response->



            when(response){



                is NetworkRequest.Loading->{
binding.loading.visibility=View.VISIBLE
                    binding.recyNazarat.visibility=View.GONE

                }



                is NetworkRequest.Success->{



                    response.data?.data?.let { data->


if (data.isNotEmpty()) {

    binding.constEmptyLay.visibility=View.GONE

binding.loading.visibility=View.GONE

    binding.recyNazarat.visibility=View.VISIBLE
    commentsAdapter.setData(data)

    binding.recyNazarat.adapter = commentsAdapter

    binding.recyNazarat.layoutManager =
        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


}else{


    binding.constEmptyLay.visibility=View.VISIBLE



    binding.recyNazarat.visibility=View.GONE




}


                    }




                }


                is NetworkRequest.Error->{
                    binding.loading.visibility=View.GONE

                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }





            }



        }
    }


}