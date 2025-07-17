package com.example.fakestoreapi.data.services

import com.example.fakestoreapi.data.model.Category
import com.example.fakestoreapi.data.model.Product
import retrofit2.http.GET

/**
 * Defines the Retrofit API endpoints for interacting with the fake store backend.
 *
 * This interface is used to fetch product and category data from a RESTful web service.
 * Retrofit generates the implementation automatically at runtime.
 *
 * Example base URL: `https://fakestoreapi.com/`
 */
interface FakeStoreApi {

    /**
     * Retrieves a list of all products available in the store.
     *
     * This is a suspending function that executes an HTTP GET request to the `products` endpoint.
     *
     * @return A list of [Product] objects containing product details such as ID, title, price, and category.
     *
     * Example request:
     * ```
     * GET https://fakestoreapi.com/products
     * ```
     *
     * Example response:
     * ```json
     * [
     *   {
     *     "id": 1,
     *     "title": "Product Title",
     *     "price": 29.99,
     *     "description": "Product description",
     *     "images": ["https://example.com/img1.jpg"],
     *     "category": { ... }
     *   },
     *   ...
     * ]
     * ```
     */
    @GET("products")
    suspend fun getAllProducts(): List<Product>

    /**
     * Retrieves all available product categories.
     *
     * This is a suspending function that executes an HTTP GET request to the `categories` endpoint.
     *
     * @return A list of [Category] objects, each representing a product category.
     *
     * Example request:
     * ```
     * GET https://fakestoreapi.com/categories
     * ```
     *
     * Example response:
     * ```json
     * [
     *   {
     *     "id": 1,
     *     "name": "Electronics",
     *     "slug": "electronics",
     *     "image": "https://example.com/cat.jpg"
     *   },
     *   ...
     * ]
     * ```
     */
    @GET("categories")
    suspend fun getCategories(): List<Category>
}
