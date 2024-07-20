/*
1. Nick Francisco
2. OSU
3. CS492
 */

package com.example.flightsearch

import android.app.Application
import com.example.flightsearch.data.FlightDatabase

class FlightsApplication : Application() {
    val database: FlightDatabase by lazy { FlightDatabase.getDatabase(this) }
}