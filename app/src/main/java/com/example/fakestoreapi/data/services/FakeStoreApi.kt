package com.example.fakestoreapi.data.services

import com.example.fakestoreapi.data.model.Category
import com.example.fakestoreapi.data.model.Product
import retrofit2.http.GET

interface FakeStoreApi {
    @GET("products")
    suspend fun getAllProducts(): List<Product>

    @GET("categories")
    suspend fun getCategories(): List<Category>
}