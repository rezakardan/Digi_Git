package com.example.digikalax.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.digikalax.R
import com.example.digikalax.data.datastore.SessionManager
import com.example.digikalax.data.models.mydigikala.BodyResponseLogin
import com.example.digikalax.data.repositoryy.mydigikala.MyDigiKalaRepository
import com.example.digikalax.databinding.ActivityMainBinding
import com.example.digikalax.viewmodel.mydigikala.MyDigiKalaViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    lateinit var binding:ActivityMainBinding


 //  private lateinit var navHost: NavHostFragment



    //Other
    private lateinit var navController: NavController





    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var body:BodyResponseLogin


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding=ActivityMainBinding.inflate(layoutInflater)
//        enableEdgeToEdge()
       setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }





   // navHost = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
          //  binding.bottomNav.setupWithNavController(navHost.navController)






        navController = findNavController(R.id.navHost)
        binding. bottomNav.setupWithNavController(navController)






        binding.bottomNav.setOnNavigationItemReselectedListener {  }










        navController.addOnDestinationChangedListener { _, destination, _ ->


    Log.d("NavController", "Destination changed to: ${destination.id}")





    binding.apply {



        when(destination.id){

          R.id.homeFragment->{        binding.bottomNav.isVisible=true }

R.id.basketShoppingFragment->{        binding.bottomNav.isVisible=true }

R.id.categoryFragment->{        binding.bottomNav.isVisible=true }

R.id.myDigiKalaFragment->{        binding.bottomNav.isVisible=true }

            R.id.profileFragment->{        binding.bottomNav.isVisible=true }
            R.id.splashFragment->{binding.bottomNav.isVisible=false}



            R.id.basketNextShoppingFragment->{binding.bottomNav.isVisible=false}

else->{ binding.bottomNav.isVisible=false}


        }
    }







}


    }













    override fun onNavigateUp(): Boolean {
        return navController.navigateUp()|| super.onNavigateUp()
    }


    }
