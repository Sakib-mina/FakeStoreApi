package com.example.fakestoreapi.data.model

/**
 * Represents a product entity retrieved from the backend API.
 *
 * This data class is commonly used in e-commerce applications to display product details,
 * manage product listings, and handle user interactions such as viewing or purchasing items.
 *
 * @property id Unique identifier for the product.
 * @property title Name or title of the product.
 * @property price Cost of the product, represented as a double value.
 * @property description Detailed textual description of the product.
 * @property images List of image URLs associated with the product.
 * @property category The category to which this product belongs.
 *
 * @see Category for category-related information.
 *
 * Example JSON:
 * ```
 * {
 *   "id": 90,
 *   "title": "Running Shoes",
 *   "price": 59.99,
 *   "description": "High-quality running shoes for all terrains.",
 *   "images": [
 *     "https://example.com/images/shoe1.jpg",
 *     "https://example.com/images/shoe2.jpg"
 *   ],
 *   "category": {
 *     "id": 4,
 *     "name": "Shoes",
 *     "slug": "shoes",
 *     "image": "https://example.com/images/category-shoes.jpg"
 *   }
 * }
 * ```
 */
data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val images: List<String>,
    val category: Category
)

