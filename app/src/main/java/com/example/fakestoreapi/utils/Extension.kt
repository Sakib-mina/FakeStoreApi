package com.example.fakestoreapi.utils

import android.widget.EditText

fun EditText.extract() : String{
    return  text.toString().trim()
}