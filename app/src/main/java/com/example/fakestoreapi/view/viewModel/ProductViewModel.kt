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

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    private var allProducts = listOf<Product>()
    private val _filteredProducts = MutableLiveData<List<Product>>()
    val filteredProducts: LiveData<List<Product>> = _filteredProducts

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    // For combined filtering
    private var currentCategoryId: Int = 0
    private var currentSearchQuery: String = ""

    init {
        fetchCategoriesAndProducts()
    }

    private fun fetchCategoriesAndProducts() {
        viewModelScope.launch {
            try {
                val cats = repository.getCategories()
                _categories.value = listOf(
                    Category(0, "All", "all", "") // All category add
                ) + cats.take(7)

                allProducts = repository.getProducts()
                _filteredProducts.value = allProducts
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            }
        }
    }

    fun updateCategoryFilter(categoryId: Int) {
        currentCategoryId = categoryId
        applyFilters()
    }

    fun updateSearchQuery(query: String) {
        currentSearchQuery = query
        applyFilters()
    }

    private fun applyFilters() {
        var filtered = allProducts

        // Category filter (skip if All selected)
        if (currentCategoryId != 0) {
            filtered = filtered.filter { it.category.id == currentCategoryId }
        }

        // Search filter (ignore case)
        if (currentSearchQuery.isNotBlank()) {
            filtered = filtered.filter {
                it.title.contains(currentSearchQuery, ignoreCase = true)
            }
        }

        _filteredProducts.value = filtered
    }
}

