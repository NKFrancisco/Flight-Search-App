/*
1. Nick Francisco
2. OSU
3. CS492
 */

package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

class OfflineFlightsRepository(private val flightDao: FlightDao): FlightsRepository {
    override fun getAllFavorites(): Flow<List<Favorite>> = flightDao.getAllFavorites()

    override fun getAllAirports(): Flow<List<Airport>> = flightDao.getAllAirports()

    override fun searchAirports(search: String): Flow<List<Airport>> = flightDao.searchAirports(search)

    override fun getFlightRoutes(search: String): Flow<List<Airport>> = flightDao.getFlightRoutes(search)

    override suspend fun insertFavorite(departure: String, destination: String) = flightDao.insert(departure, destination)
}