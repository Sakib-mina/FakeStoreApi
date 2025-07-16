package com.example.fakestoreapi.data.repository

import com.example.fakestoreapi.data.model.Category
import com.example.fakestoreapi.data.services.FakeStoreApi
import com.example.fakestoreapi.data.model.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: FakeStoreApi
) {
    suspend fun getProducts(): List<Product> = api.getAllProducts()
    suspend fun getCategories(): List<Category> = api.getCategories()
}