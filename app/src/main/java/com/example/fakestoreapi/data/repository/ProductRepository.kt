package com.example.fakestoreapi.data.repository

import com.example.fakestoreapi.data.model.Category
import com.example.fakestoreapi.data.services.FakeStoreApi
import com.example.fakestoreapi.data.model.Product
import javax.inject.Inject


/**
 * Repository class responsible for handling all data-related operations
 * between the UI/ViewModel layer and the FakeStore API service.
 *
 * Follows the Repository design pattern to abstract the data source,
 * making the codebase clean, modular, and testable.
 *
 * This class is injectable via Hilt, and uses Retrofit to fetch data asynchronously.
 *
 * @property api Injected instance of the FakeStoreApi (Retrofit interface).
 *
 * @constructor Injects the FakeStoreApi instance via constructor using Hilt.
 */
class ProductRepository @Inject constructor(
    private val api: FakeStoreApi
) {

    /**
     * Fetches the full list of products from the remote API.
     *
     * @return A list of [Product] objects.
     * @throws Exception if network call fails.
     */
    suspend fun getProducts(): List<Product> = api.getAllProducts()

    /**
     * Retrieves a list of available product categories from the API.
     *
     * @return A list of [Category] objects.
     * @throws Exception if network call fails.
     */
    suspend fun getCategories(): List<Category> = api.getCategories()
}