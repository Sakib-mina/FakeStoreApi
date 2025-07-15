package com.example.fakestoreapi.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.fakestoreapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main entry point of the application UI.
 *
 * Annotated with [AndroidEntryPoint] to enable Hilt-based dependency injection
 * into this activity.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // ViewBinding instance for accessing layout views
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Optional: Enables edge-to-edge content if you're using modern design (API 29+)
        enableEdgeToEdge()

        // Inflate the layout using ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}