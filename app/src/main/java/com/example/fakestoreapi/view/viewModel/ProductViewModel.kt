package com.example.fakestoreapi.view.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreapi.data.model.Category
import com.example.fakestoreapi.data.model.Product
import com.example.fakestoreapi.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class responsible for managing product and category data.
 *
 * Uses Hilt for dependency injection to provide the ProductRepository.
 * Exposes LiveData for categories, filtered products, and error messages.
 *
 * Supports combined filtering by category and search query.
 */
@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    // LiveData holding the list of product categories, observed by the UI.
    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    // Holds all fetched products internally for filtering operations.
    private var allProducts = listOf<Product>()

    // LiveData exposing the currently filtered list of products.
    private val _filteredProducts = MutableLiveData<List<Product>>()
    val filteredProducts: LiveData<List<Product>> = _filteredProducts

    // LiveData for error messages to notify the UI in case of failures.
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    // Variables to track current filter states.
    private var currentCategoryId: Int = 0
    private var currentSearchQuery: String = ""

    init {
        // Initial data fetch on ViewModel creation.
        fetchCategoriesAndProducts()
    }

    /**
     * Fetch categories and products from the repository asynchronously.
     * Adds a synthetic "All" category at the top for filtering all products.
     *
     * Handles exceptions by posting error messages.
     */
    private fun fetchCategoriesAndProducts() {
        viewModelScope.launch {
            try {
                // Fetch categories, prepend "All" category for UI filter option.
                val cats = repository.getCategories()
                _categories.value = listOf(
                    Category(0, "All", "all", "") // Synthetic 'All' category
                ) + cats.take(7) // Limit to first 7 categories (optional)

                // Fetch all products
                allProducts = repository.getProducts()

                // Initially, no filter applied; show all products.
                _filteredProducts.value = allProducts
            } catch (e: Exception) {
                // Post error message for UI to observe and display
                _error.value = e.localizedMessage
            }
        }
    }

    /**
     * Updates the currently selected category filter.
     *
     * @param categoryId The ID of the selected category (0 = All)
     */
    fun updateCategoryFilter(categoryId: Int) {
        currentCategoryId = categoryId
        applyFilters()
    }

    /**
     * Updates the current search query filter.
     *
     * @param query The text input from search bar to filter products by title.
     */
    fun updateSearchQuery(query: String) {
        currentSearchQuery = query
        applyFilters()
    }

    /**
     * Applies the combined filters (category and search query) on the product list.
     * Updates the _filteredProducts LiveData for the UI to reactively update.
     */
    private fun applyFilters() {
        var filtered = allProducts

        // Apply category filter unless "All" category (id=0) is selected.
        if (currentCategoryId != 0) {
            filtered = filtered.filter { it.category.id == currentCategoryId }
        }

        // Apply case-insensitive search filter on product title if query is not blank.
        if (currentSearchQuery.isNotBlank()) {
            filtered = filtered.filter {
                it.title.contains(currentSearchQuery, ignoreCase = true)
            }
        }

        _filteredProducts.value = filtered
    }
}
