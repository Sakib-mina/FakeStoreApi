package com.example.fakestoreapi.data.pref

/**
 * A singleton object that holds constant keys used throughout the app,
 * particularly for token management and secure data storage.
 *
 * This object helps avoid hardcoded strings in multiple places and ensures
 * consistency when storing or retrieving sensitive values like authentication tokens.
 *
 * Usage examples:
 * ```
 * val token = sharedPreferences.getString(Keys.ACCESS_TOKEN, null)
 * sharedPreferences.edit().putString(Keys.REFRESH_TOKEN, "new_refresh_token").apply()
 * ```
 *
 * @property ACCESS_TOKEN Key used to store the user's access token in SharedPreferences or DataStore.
 * @property REFRESH_TOKEN Key used to store the user's refresh token in persistent storage.
 */
object Keys {
    const val ACCESS_TOKEN = "ACCESS_TOKEN"
    const val REFRESH_TOKEN = "REFRESH_TOKEN"
}
