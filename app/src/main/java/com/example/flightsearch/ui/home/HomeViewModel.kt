/*
1. Nick Francisco
2. OSU
3. CS492
 */

package com.example.flightsearch.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightsApplication
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.data.FlightDao
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val flightDao: FlightDao) : ViewModel() {

    //Get All Airports
    fun getAllAirports(): Flow<List<Airport>> = flightDao.getAllAirports()

    //Get All Favorites
    fun getALlFavorites(): Flow<List<Favorite>> = flightDao.getAllFavorites()

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightsApplication)
                HomeViewModel(application.database.flightDao())
            }
        }
    }
}