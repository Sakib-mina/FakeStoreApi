package com.example.fakestoreapi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/**
 * Application class for initializing Hilt dependency injection.
 *
 * This class is required to use Hilt in an Android application.
 * Annotated with [HiltAndroidApp], which triggers Hilt's code generation,
 * including a base class for your application that serves as the application-level dependency container.
 */
@HiltAndroidApp
class FakeStore : Application()