package com.example.fakestoreapi

import android.widget.EditText

fun EditText.extract() : String{
    return  text.toString().trim()
}