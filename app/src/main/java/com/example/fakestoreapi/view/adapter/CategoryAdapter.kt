package com.example.fakestoreapi.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.fakestoreapi.data.model.Category
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.CategoryItemBinding

class CategoryAdapter(
    private val categoryList: List<Category>,
    private val onItemClick: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedPosition = 0

    inner class CategoryViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category, position: Int) {
            binding.categoryName.text = category.name
            if (category.image.isNotEmpty()) {
                binding.categoryImage.load(category.image) {
                    placeholder(R.drawable.ic_launcher_foreground)
                    error(R.drawable.ic_launcher_foreground)
                    transformations(CircleCropTransformation())  // এটা method call, ভিতরে কিছু লাগে না
                }
            } else {
                binding.categoryImage.setImageResource(R.drawable.ic_launcher_foreground)
            }

            // Highlight selected category (optional)
            binding.root.isSelected = selectedPosition == position

            binding.root.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)

                onItemClick(category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position], position)
    }

    override fun getItemCount(): Int = categoryList.size
}
