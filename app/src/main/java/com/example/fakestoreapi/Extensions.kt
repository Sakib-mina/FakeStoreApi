package com.example.fakestoreapi

import android.widget.ImageView
import coil.load
import coil.request.CachePolicy

fun ImageView.load(imageUrl: String){

    this.load(imageUrl){
        placeholder(R.drawable.profile_img)
        error(R.drawable.profile_img)
        diskCachePolicy(CachePolicy.ENABLED)
    }
}