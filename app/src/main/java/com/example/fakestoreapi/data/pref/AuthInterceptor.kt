package com.example.fakestoreapi.data.pref

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * AuthInterceptor adds an Authorization header to every HTTP request.
 *
 * This interceptor is useful for APIs that require a Bearer token for authenticated access.
 * It retrieves the access token from a shared preference manager (`PrefManager`) and
 * attaches it to the "Authorization" header in the format: `Bearer <access_token>`.
 *
 * @constructor Creates an instance with a dependency on PrefManager (injected via Hilt or Dagger).
 * @param prefManager An instance of PrefManager used to fetch the access token.
 *
 * Example usage in Retrofit:
 * ```
 * OkHttpClient.Builder()
 *     .addInterceptor(AuthInterceptor(prefManager))
 *     .build()
 * ```
 */
class AuthInterceptor @Inject constructor(
    private var prefManager: PrefManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request()
            .newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${prefManager.getPref(Keys.ACCESS_TOKEN)}"
            )

        return chain.proceed(requestBuilder.build())
    }
}
