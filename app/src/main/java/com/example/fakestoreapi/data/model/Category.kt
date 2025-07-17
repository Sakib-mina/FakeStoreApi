package com.example.fakestoreapi.data.model

/**
 * Represents a product category in the application.
 *
 * Each category is used to group related products together,
 * allowing users to filter and browse items more efficiently.
 * This model is typically received from an API response and linked to each product.
 *
 * @property id Unique identifier for the category.
 * @property name The display name of the category (e.g., "Shoes", "Electronics").
 * @property slug A URL-friendly version of the category name, useful for routing or API filtering.
 * @property image A URL pointing to the category’s thumbnail or representative image.
 *
 * Example JSON:
 * ```
 * {
 *   "id": 4,
 *   "name": "Shoes",
 *   "slug": "shoes",
 *   "image": "https://example.com/images/category-shoes.jpg"
 * }
 * ```
 *
 * Usage:
 * - Display category name and image in horizontal RecyclerView
 * - Filter products by category
 * - Use `slug` for backend filtering or navigation purposes
 */
data class Category(
    val id: Int,
    val name: String,
    val slug: String,
    val image: String
)

