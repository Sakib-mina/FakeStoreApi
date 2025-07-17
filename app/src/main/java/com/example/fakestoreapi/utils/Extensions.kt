package com.example.fakestoreapi.utils

import android.widget.ImageView
import coil.load
import coil.request.CachePolicy
import com.example.fakestoreapi.R

/**
 * Extension function to load an image URL into an ImageView using Coil library.
 *
 * This method applies:
 * - A placeholder image while loading
 * - An error image if loading fails
 * - Disk caching enabled for better performance
 *
 * @param imageUrl The URL of the image to load into the ImageView.
 */
fun ImageView.load(imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) {
        // Load placeholder if URL is null or empty
        this.setImageResource(R.drawable.profile_img)
    } else {
        this.load(imageUrl) {
            placeholder(R.drawable.profile_img)
            error(R.drawable.profile_img)
            diskCachePolicy(CachePolicy.ENABLED)
        }
    }
}
