/*
1. Nick Francisco
2. OSU
3. CS492
 */

package com.example.flightsearch.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.flightsearch.data.Airport
import com.example.flightsearch.ui.home.HomeScreen
import com.example.flightsearch.ui.search.SearchScreen


/**
 * Enum values that represent the screens in the app
 */
enum class FlightScreen {
    Start,
    Search
}

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun FlightNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    var airportInput by remember {
        mutableStateOf(Airport(id = 0, name = "", iata_code = "", passengers = 0))
    }
    NavHost(
        navController = navController,
        startDestination = FlightScreen.Start.name,
        modifier = modifier
    ) {
        composable(route = FlightScreen.Start.name) {
            HomeScreen(onRowClicked = {
                airportInput = it
                navController.navigate(FlightScreen.Search.name)
            })
        }
        composable(route = FlightScreen.Search.name) {
            SearchScreen(departingAirport = airportInput)
        }
    }
}