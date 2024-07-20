/*
1. Nick Francisco
2. OSU
3. CS492
 */

package com.example.flightsearch.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "airport")
data class Airport(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val iata_code: String,
    val passengers: Int
)