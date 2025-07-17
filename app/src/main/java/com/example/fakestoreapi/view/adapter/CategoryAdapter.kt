package com.example.fakestoreapi.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.fakestoreapi.data.model.Category
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.CategoryItemBinding

/**
 * RecyclerView Adapter to display a list of categories with selectable highlighting.
 *
 * This adapter binds a list of [Category] objects to a RecyclerView, displaying the category name
 * and image in a circular cropped format. It supports selecting a single category at a time and
 * highlights the selected item.
 *
 * @param categoryList List of categories to display.
 * @param onItemClick Lambda callback invoked when a category item is clicked, providing the selected [Category].
 *
 * Usage:
 * ```kotlin
 * val adapter = CategoryAdapter(categories) { selectedCategory ->
 *     // Handle category click event here
 * }
 * recyclerView.adapter = adapter
 * ```
 */
class CategoryAdapter(
    private val categoryList: List<Category>,
    private val onItemClick: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    // Holds the currently selected category position for highlighting purposes
    private var selectedPosition = 0

    /**
     * ViewHolder class for category item views.
     *
     * @property binding ViewBinding instance for category item layout.
     */
    inner class CategoryViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds a [Category] object to the item view.
         *
         * Sets the category name and loads the category image using Coil with a circular crop transformation.
         * Handles item selection highlighting and click events.
         *
         * @param category The category data to bind.
         * @param position The position of the item within the adapter.
         */
        fun bind(category: Category, position: Int) {
            binding.categoryName.text = category.name

            if (category.image.isNotEmpty()) {
                binding.categoryImage.load(category.image) {
                    placeholder(R.drawable.ic_launcher_foreground)  // Shown while image loads
                    error(R.drawable.ic_launcher_foreground)        // Shown if loading fails
                    transformations(CircleCropTransformation())    // Circular crop transformation for image
                }
            } else {
                // Fallback image if no URL is provided
                binding.categoryImage.setImageResource(R.drawable.ic_launcher_foreground)
            }

            // Highlight the selected item for better UX
            binding.root.isSelected = selectedPosition == position

            // Handle click on category item
            binding.root.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = position
                // Notify adapter to refresh both previously selected and newly selected items
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)

                // Invoke the external callback with the clicked category
                onItemClick(category)
            }
        }
    }

    /**
     * Creates a new ViewHolder instance by inflating the layout using ViewBinding.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    /**
     * Binds the data at the specified position to the ViewHolder.
     */
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position], position)
    }

    /**
     * Returns the total number of categories in the list.
     */
    override fun getItemCount(): Int = categoryList.size
}
