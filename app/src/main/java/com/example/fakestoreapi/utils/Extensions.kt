package com.example.fakestoreapi.utils

import android.widget.ImageView
import coil.load
import coil.request.CachePolicy
import com.example.fakestoreapi.R

fun ImageView.load(imageUrl: String){

    this.load(imageUrl){
        placeholder(R.drawable.profile_img)
        error(R.drawable.profile_img)
        diskCachePolicy(CachePolicy.ENABLED)
    }
}