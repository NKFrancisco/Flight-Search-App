/*
1. Nick Francisco
2. OSU
3. CS492
 */

package com.example.flightsearch.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightsApplication
import com.example.flightsearch.ui.home.HomeViewModel


/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {

    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [FlightsApplication].
 */
fun CreationExtras.flightsApplication(): FlightsApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightsApplication)

