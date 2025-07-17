package com.example.fakestoreapi.view.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fakestoreapi.view.adapter.CategoryAdapter
import com.example.fakestoreapi.view.adapter.ProductAdapter
import com.example.fakestoreapi.view.viewModel.ProductViewModel
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.FragmentHomeBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Indicates that Hilt should inject dependencies into this Fragment.
class HomeFragment : Fragment() {

    // View Binding instance for accessing layout views.
    // 'lateinit' means it will be initialized before use.
    private lateinit var binding: FragmentHomeBinding

    // ViewModel instance for this Fragment. 'by viewModels()' is a Kotlin extension
    // from Activity-Ktx/Fragment-Ktx that provides a ViewModel.
    private val viewModel: ProductViewModel by viewModels()

    // Adapters for displaying product and category lists in RecyclerViews.
    // 'lateinit' because they are initialized in onViewCreated.
    private lateinit var productAdapter: ProductAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    // --- Lifecycle Methods ---

    /**
     * Called to have the fragment instantiate its user interface view.
     * This method inflates the layout for the fragment.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {
        // Inflate the layout using View Binding and return the root view.
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Called immediately after onCreateView() has returned, but before any saved state has been restored into the view.
     * This is where you typically set up your views, adapters, and observe LiveData.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState) // Always call super.onViewCreated

        // --- RecyclerView Setup ---
        // Configure the RecyclerView for displaying categories horizontally.
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        // Configure the RecyclerView for displaying products in a 2-column grid.
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        // --- LiveData Observers ---

        /**
         * Observes the LiveData for the list of categories from the ViewModel.
         * When the category list changes, the CategoryAdapter is initialized/updated
         * and set to the category RecyclerView.
         *
         * The CategoryAdapter's click listener updates the category filter in the ViewModel.
         */
        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            // Initialize CategoryAdapter with the fetched categories and a click listener.
            // The click listener calls updateCategoryFilter in the ViewModel with the selected category's ID.
            categoryAdapter = CategoryAdapter(categories) { category ->
                viewModel.updateCategoryFilter(category.id)
            }
            // Set the adapter to the category RecyclerView.
            binding.categoryRecyclerView.adapter = categoryAdapter
        }

        /**
         * Observes the LiveData for the filtered product list from the ViewModel.
         * This list changes based on category selection and search queries.
         * When the filtered product list changes, the ProductAdapter is initialized/updated
         * and set to the product RecyclerView.
         *
         * The ProductAdapter's click listener navigates to the ProductDetailsFragment,
         * passing the clicked product's data as a JSON string in a Bundle.
         */
        viewModel.filteredProducts.observe(viewLifecycleOwner) { products ->
            // Initialize ProductAdapter with the filtered products and a click listener.
            // The click listener serializes the product object to JSON using Gson,
            // puts it into a Bundle, and navigates to ProductDetailsFragment.
            productAdapter = ProductAdapter(products) { product ->
                val productJson = Gson().toJson(product) // Convert product object to JSON string
                val bundle = Bundle().apply { putString("product", productJson) } // Create a Bundle and put the JSON string
                findNavController().navigate(R.id.action_homeFragment_to_productDetailsFragment, bundle) // Navigate with the Bundle
            }
            // Set the adapter to the main product RecyclerView.
            binding.recyclerView.adapter = productAdapter
        }

        // --- UI Listeners ---

        /**
         * Adds a TextWatcher to the searchBox (an EditText typically).
         * As the user types, the text changes trigger a call to updateSearchQuery in the ViewModel,
         * which then filters the products based on the new query.
         */
        binding.searchBox.addTextChangedListener { editable ->
            // Update the search query in the ViewModel whenever the text in the search box changes.
            viewModel.updateSearchQuery(editable.toString())
        }

        /**
         * Observes the LiveData for error messages from the ViewModel.
         * If an error occurs (e.g., failed network request), a Toast message is displayed
         * to the user with the error details.
         */
        viewModel.error.observe(viewLifecycleOwner) {
            // Display a short Toast message when an error is observed from the ViewModel.
            Toast.makeText(requireContext(), "Error: $it", Toast.LENGTH_SHORT).show()
        }
    }
}
