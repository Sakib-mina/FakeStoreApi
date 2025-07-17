package com.example.fakestoreapi.utils

import android.widget.EditText

/**
 * Extension function to extract trimmed text from an EditText.
 *
 * This function simplifies getting user input by converting the EditText's text to a
 * trimmed String (removing leading and trailing whitespace).
 *
 * @return The user-entered text as a trimmed [String].
 *
 * Example usage:
 * ```
 * val name = binding.nameEditText.extract()
 * if (name.isNotEmpty()) { ... }
 * ```
 */
fun EditText.extract(): String {
    return text.toString().trim()
}
