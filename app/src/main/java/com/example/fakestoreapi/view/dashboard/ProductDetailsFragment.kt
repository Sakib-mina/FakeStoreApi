package com.example.fakestoreapi.view.dashboard

import ImageSliderAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fakestoreapi.data.model.Product
import com.example.fakestoreapi.databinding.FragmentProductDetailsBinding
import com.example.fakestoreapi.utils.load
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        // Get argument using Bundle
        val productJson = arguments?.getString("product")
        val product = Gson().fromJson(productJson, Product::class.java)

        // Setup UI
        binding.tvTitle.text = product.title
        binding.tvPrice.text = "$${product.price}"
        binding.tvDescription.text = product.description
        binding.tvCategoryName.text = product.category.name
        binding.ivCategoryImage.load(product.category.image)

        binding.viewPagerImages.adapter = ImageSliderAdapter(product.images)

        return binding.root
    }

    private fun formatDate(dateStr: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            inputFormat.timeZone = TimeZone.getTimeZone("UTC")
            val date = inputFormat.parse(dateStr)
            val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
            outputFormat.format(date!!)
        } catch (e: Exception) {
            dateStr
        }
    }
}