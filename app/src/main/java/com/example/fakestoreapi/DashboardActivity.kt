package com.example.fakestoreapi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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

    }
}
