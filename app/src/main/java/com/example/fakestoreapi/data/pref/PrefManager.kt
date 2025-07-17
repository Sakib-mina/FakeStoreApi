package com.example.fakestoreapi.data.pref

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


/**
 * PrefManager is a wrapper class around Android's SharedPreferences that provides
 * convenient methods for saving and retrieving key-value pairs, particularly
 * useful for session management, token storage, and user preferences.
 *
 * This class is marked with `@Inject` so it can be provided by Hilt Dependency Injection,
 * and it uses `@ApplicationContext` to ensure the application-level context is used.
 *
 * @constructor Injects the context using Hilt's ApplicationContext.
 *
 * @property pref The SharedPreferences instance tied to "app-prefs".
 *
 * Usage:
 * ```
 * prefManager.setPref("user_id", "123")
 * val token = prefManager.getPref("access_token")
 * if (prefManager.isLoggedIn()) { ... }
 * ```
 */
class PrefManager @Inject constructor(@ApplicationContext context: Context) {

    private val pref = context.getSharedPreferences("app-prefs", Context.MODE_PRIVATE)

    /**
     * Stores a key-value pair as a String into SharedPreferences.
     *
     * @param key The key name.
     * @param value The String value to be stored.
     */
    fun setPref(key: String, value: String) {
        pref.edit().putString(key, value).apply()
    }

    /**
     * Retrieves a stored String value based on the given key.
     *
     * @param key The key name.
     * @return The stored value or an empty string if not found.
     */
    fun getPref(key: String): String {
        return pref.getString(key, "") ?: ""
    }

    /**
     * Determines whether a user is currently logged in by checking
     * if the access token is present and non-blank.
     *
     * @return True if logged in, false otherwise.
     */
    fun isLoggedIn(): Boolean =
        getPref(Keys.ACCESS_TOKEN).isNotBlank()

    /**
     * Clears all stored preferences (e.g. during logout).
     */
    fun clear() {
        pref.edit().clear().apply()
    }
}