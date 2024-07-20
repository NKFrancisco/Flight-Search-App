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
@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val departure_code: String,
    val destination_code: String
)