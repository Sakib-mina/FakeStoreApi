package com.example.fakestoreapi.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.fakestoreapi.data.model.Product
import com.example.fakestoreapi.databinding.ItemProductBinding

/**
 * RecyclerView Adapter for displaying a list of products with filtering support.
 *
 * This adapter maintains an original full list of products and a filtered list
 * which updates based on search queries or filters.
 * It supports item click events via a callback function.
 *
 * @param originalList The full list of products to display.
 * @param onItemClick Lambda callback invoked when a product item is clicked.
 */
class ProductAdapter(
    private val originalList: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // Current filtered list shown in the RecyclerView
    private var filteredList: List<Product> = originalList.toList()

    /**
     * ViewHolder for the product item layout.
     * Uses View Binding for efficient view reference.
     */
    inner class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    /**
     * Inflates the product item layout and creates a ViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    /**
     * Binds product data to the views inside the ViewHolder.
     *
     * Displays product title, price with currency symbol, category slug, and the first product image.
     * Sets up click listener to propagate clicks via the onItemClick lambda.
     */
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = filteredList[position]
        holder.binding.titleText.text = product.title
        holder.binding.priceText.text = "$${product.price}"
        holder.binding.category.text = product.category.slug
        // Load the first image from the product images list using Coil or Glide
        holder.binding.imageView.load(product.images.firstOrNull())

        holder.itemView.setOnClickListener {
            onItemClick(product)
        }
    }

    /**
     * Returns the number of items currently shown (filtered list size).
     */
    override fun getItemCount(): Int = filteredList.size

    /**
     * Filters the product list based on the query string.
     *
     * If the query is empty, the original full list is restored.
     * Otherwise, products whose title contains the query string (case-insensitive) are kept.
     *
     * After filtering, notifies the adapter to refresh the RecyclerView.
     *
     * @param query The search string to filter product titles.
     */
    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            originalList
        } else {
            originalList.filter {
                it.title.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}
