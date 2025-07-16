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

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: ProductViewModel by viewModels()

    private lateinit var productAdapter: ProductAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Setup RecyclerViews
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        // Observe categories
        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            categoryAdapter = CategoryAdapter(categories) { category ->
                viewModel.updateCategoryFilter(category.id)
            }
            binding.categoryRecyclerView.adapter = categoryAdapter
        }



        // Observe filtered product list
        viewModel.filteredProducts.observe(viewLifecycleOwner) { products ->
            productAdapter = ProductAdapter(products) { product ->
                val productJson = Gson().toJson(product)
                val bundle = Bundle().apply { putString("product", productJson) }
                findNavController().navigate(R.id.action_homeFragment_to_productDetailsFragment, bundle)
            }
            binding.recyclerView.adapter = productAdapter
        }

        // Search box listener
        binding.searchBox.addTextChangedListener { editable ->
            viewModel.updateSearchQuery(editable.toString())
        }

        // Error observe
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Error: $it", Toast.LENGTH_SHORT).show()
        }
    }
}
