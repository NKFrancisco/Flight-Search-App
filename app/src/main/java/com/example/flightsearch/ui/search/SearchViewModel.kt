/*
1. Nick Francisco
2. OSU
3. CS492
 */

package com.example.flightsearch.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightsApplication
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.FlightDao
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch

class SearchViewModel(private val flightDao: FlightDao) : ViewModel() {

    // Get all Airports
    fun getAllAirports(): Flow<List<Airport>> = flightDao.getAllAirports()

    // Get flight routes
    fun getFlightRoutes(search: String): Flow<List<Airport>> = flightDao.getFlightRoutes(search)

    // Insert favorite
    suspend fun saveFavorite(departure: String, destination: String) {
        viewModelScope.launch {
            flightDao.insert(departure, destination)
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightsApplication)
                SearchViewModel(application.database.flightDao())
            }
        }
    }
}