/*
1. Nick Francisco
2. OSU
3. CS492
 */


package com.example.flightsearch.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val flightsRepository: FlightsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    /**
     * Implementation for [FlightsRepository]
     */
    override val flightsRepository: FlightsRepository by lazy {
        OfflineFlightsRepository(FlightDatabase.getDatabase(context).flightDao())
    }
}