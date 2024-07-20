package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {

    //Get all favorites by name asc
    @Query("SELECT * from favorite ORDER BY departure_code ASC")
    fun getAllFavorites(): Flow<List<Favorite>>

    //Get all airports by name asc
    @Query("SELECT * from airport ORDER BY id ASC")
    fun getAllAirports(): Flow<List<Airport>>

    //Search for airport with iata_code
    @Query("SELECT * from airport WHERE iata_code = :search OR name LIKE '%:search%'")
    fun searchAirports(search: String): Flow<List<Airport>>

    //Get All airports besides given iata_code
    @Query("SELECT * FROM airport WHERE iata_code != :search")
    fun getFlightRoutes(search: String): Flow<List<Airport>>

    //Insert favorite into database
    @Query("INSERT INTO favorite (departure_code, destination_code) VALUES (:departure, :destination)")
    suspend fun insert(departure: String, destination: String)

}