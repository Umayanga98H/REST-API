package com.hiumayanga.api_practical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.hiumayanga.api_practical.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  var _binding:ActivityMainBinding? = null

    private val binding get()=_binding!!
    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding!!.root)

        val navHostFragment =supportFragmentManager.findFragmentById(R.id.nav_host_fragment)as NavHostFragmment
        navController =naveHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onNavigateUp():Boolean{
        return navController.navigateUp() || super.onNavigateUp()
    }
}