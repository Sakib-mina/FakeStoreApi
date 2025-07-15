package com.example.fakestoreapi.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.ActivityDashboardBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * DashboardActivity serves as the main screen after user login.
 *
 * It uses ViewBinding to access UI elements and is ready to inject
 * dependencies using Dagger Hilt with [@AndroidEntryPoint].
 */
@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    // ViewBinding instance for accessing views safely
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables edge-to-edge content (optional for immersive UI)
        enableEdgeToEdge()

        // Inflate the layout using ViewBinding
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView3) as NavHostFragment

        // Get the NavController from the NavHostFragment
        val navController = navHostFragment.navController

        // Setup the BottomNavigationView to work with NavController
        // This allows navigation between fragments using the bottom nav menu
        binding.bottomNav.setupWithNavController(navController)
    }
}
