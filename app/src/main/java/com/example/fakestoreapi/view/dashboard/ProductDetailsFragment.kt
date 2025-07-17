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

/**
 * [ProductDetailsFragment] displays the detailed information of a single product.
 * It receives product data via arguments passed to the fragment.
 */
@AndroidEntryPoint // Marks this Fragment as an Android entry point for Hilt to inject dependencies.
class ProductDetailsFragment : Fragment() {

    // ViewBinding instance for the fragment's layout.
    // 'lateinit' means it will be initialized later, typically in onCreateView.
    private lateinit var binding: FragmentProductDetailsBinding

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is where you inflate the layout and perform initial setup.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return The View for the fragment's UI, or null.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using ViewBinding.
        // This attaches the layout XML (fragment_product_details.xml) to the fragment.
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        // --- Data Retrieval ---
        // Retrieve the product data passed as arguments to this fragment.
        // The product data is expected to be in JSON format under the key "product".
        val productJson = arguments?.getString("product")
        // Convert the JSON string back into a Product data class object using Gson.
        val product = Gson().fromJson(productJson, Product::class.java)

        // --- UI Setup ---
        // Populate the UI elements with the product details.
        binding.tvTitle.text = product.title // Set the product title.
        binding.tvPrice.text = "$${product.price}" // Set the product price, prefixed with "$".
        binding.tvDescription.text = product.description // Set the product description.
        binding.tvCategoryName.text = product.category.name // Set the product's category name.
        // Load the category image using Coil (an image loading library).
        binding.ivCategoryImage.load(product.category.image)

        // Set up the ViewPager for displaying product images.
        // An ImageSliderAdapter is used to handle the list of product images.
        binding.viewPagerImages.adapter = ImageSliderAdapter(product.images)

        // Return the root view of the fragment's layout.
        return binding.root
    }

    /**
     * Formats a given date string from a specific input format to a more readable output format.
     * Handles potential parsing errors by returning the original string if formatting fails.
     *
     * @param dateStr The input date string in "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" format (UTC).
     * @return A formatted date string in "dd MMM yyyy, HH:mm" format, or the original string if parsing fails.
     */
    private fun formatDate(dateStr: String): String {
        return try {
            // Define the input date format, expecting UTC timezone.
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            inputFormat.timeZone = TimeZone.getTimeZone("UTC")
            // Parse the input date string into a Date object.
            val date = inputFormat.parse(dateStr)
            // Define the desired output date format.
            val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
            // Format the Date object into the output string.
            outputFormat.format(date!!) // '!!' used as parse() can return null, but we're in a try-catch.
        } catch (e: Exception) {
            // If any exception occurs during parsing or formatting, return the original string.
            dateStr
        }
    }
}