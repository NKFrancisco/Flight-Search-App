/*
1. Nick Francisco
2. OSU
3. CS492
 */


package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

interface FlightsRepository {

    fun getAllFavorites(): Flow<List<Favorite>>

    fun getAllAirports(): Flow<List<Airport>>

    fun searchAirports(search: String): Flow<List<Airport>>

    fun getFlightRoutes(search: String): Flow<List<Airport>>

    suspend fun insertFavorite(departure: String, destination: String)
}