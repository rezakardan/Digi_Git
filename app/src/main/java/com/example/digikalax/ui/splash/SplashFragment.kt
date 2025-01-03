package com.example.digikalax.ui.splash

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.digikalax.R
import com.example.digikalax.databinding.FragmentSplashBinding
import com.example.digikalax.utils.extentions.singleObserve
import com.example.digikalax.utils.network.NetworkChecker
import com.example.digikalax.viewmodel.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {


    lateinit var binding: FragmentSplashBinding


    @Inject
    lateinit var isNetworkAvailable: NetworkChecker








    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)







lifecycleScope.launch {




isNetworkAvailable.checkNetwork().collect{


    if (it){



        findNavController().navigate(R.id.homeFragment)



    }


}

}











            binding.lottieAnimationView.apply {

                addAnimatorListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {

                    }

                    override fun onAnimationEnd(animation: Animator) {


                        lifecycleScope.launch {

                            delay(2000)
                            playAnimation()
                        }
                    }

                    override fun onAnimationCancel(animation: Animator) {
                    }

                    override fun onAnimationRepeat(animation: Animator) {
                    }


                })


            }





    }








}