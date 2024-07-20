/*
1. Nick Francisco
2. OSU
3. CS492
 */

package com.example.flightsearch.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.ui.search.SearchScreen


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.factory),
    onRowClicked: (Airport) -> Unit,
) {

    // Favorites
    val favorites by viewModel.getALlFavorites().collectAsState(emptyList())
    //Airports
    val airports by viewModel.getAllAirports().collectAsState(emptyList())

    HomeBody(
        favoriteList = favorites,
        airportList = airports,
        onClick = { onRowClicked(it) },
        modifier = modifier
            .fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeBody(
    favoriteList: List<Favorite>,
    airportList: List<Airport>,
    onClick: (Airport) -> Unit,
    modifier: Modifier = Modifier
) {
    // Search input
    var searchInput by remember { mutableStateOf("")}
    // Selected airport for route list
    var airportInput by remember {
        mutableStateOf(Airport(id = 0, name = "", iata_code = "", passengers = 0))
    }
    // Used to display route list
    var displayRoutes by remember { mutableStateOf(false)}


    Column (
        modifier = modifier
    ) {

        TextField(
            value = searchInput,
            onValueChange = { searchInput = it },
            placeholder = { Text("Enter departure airport") },
            modifier = Modifier.fillMaxWidth()
        )

        // If empty search don't show routes
        if(searchInput == "") {
            displayRoutes = false
        }

        //If search row clicked
        if (displayRoutes == true) {
            SearchScreen(departingAirport = airportInput)
        }

        // If user searching
        if (searchInput != "") {
            SearchList(
                airportList = airportList,
                searchString = searchInput,
                onClick = {
                    airportInput= it
                    displayRoutes = true

                }
            )
        // Else display favorites ...
        } else {

            // If empty favorite list
            if (favoriteList.isEmpty()) {

                Text(
                    text = "No Favorites",
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                    fontWeight = FontWeight.Bold,
                )
            // Else have favorites
            } else {
                FavoriteList(
                    //favoriteList = favoriteList,
                    favoriteList = favoriteList,
                    airportList = airportList,
                    //onItemClick = {  },
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun FavoriteList(
    favoriteList: List<Favorite>,
    airportList: List<Airport>,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Favorite routes",
        modifier.padding(start = 10.dp, top = 10.dp),
        fontWeight = FontWeight.Bold,
    )

    LazyColumn(modifier = modifier) {
        items(items = favoriteList, key = { "fl-${it.id}" }) {item ->
            val departingAirport = airportList.first { it.iata_code == item.departure_code }
            val destinationAirport = airportList.first { it.iata_code == item.destination_code }
            FavoriteFlight(
                item = item,
                departingAirport = departingAirport.name,
                destinationAirport = destinationAirport.name,
                modifier = modifier
            )  
        }
    }
}

@Composable
fun FavoriteFlight(
item : Favorite,
departingAirport: String,
destinationAirport: String,
modifier: Modifier = Modifier
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(15.dp)
    ) {
        Row (modifier = modifier.padding(6.dp)) {
            // Depart and Arrive Info
            Column(
                modifier = modifier
                    .padding(start = 6.dp)
                    .weight(1f)
            ) {
                Row {
                    Text(text = "Depart")
                }
                Row {
                    Text(text = item.departure_code)
                    Spacer(modifier = modifier.width(10.dp))
                    Text(text = departingAirport)
                }
                Row {
                    Text(text = "Arrive")
                }
                Row {
                    Text(text = item.destination_code)
                    Spacer(modifier = modifier.width(10.dp))
                    Text(destinationAirport)
                }
            }
            // Favorite Icon
            IconButton(
                onClick = { /*TODO Delete?*/ },
                modifier = modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    tint = Color.Yellow,
                    contentDescription = "Favorite Icon"
                )
            }
        }
    }
}

@Composable
fun SearchList(
    airportList: List<Airport>,
    searchString: String,
    onClick: (Airport) -> Unit,
    modifier: Modifier = Modifier
) {

    val filteredAirports = airportList.filter {
            airport -> airport.name.contains(searchString, ignoreCase = true)
            || airport.iata_code.contains(searchString, ignoreCase = true)
    }

    LazyColumn(modifier = modifier) {
        items(items = filteredAirports, key = { "sl-${it.id}" }) {item ->
            SearchItem(
                item = item,
                onClick = { onClick(it) }
            )
        }
    }

}

@Composable
fun SearchItem(
    item: Airport,
    onClick: (Airport) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier
        .clickable { onClick(item) }
        .padding(4.dp))
    {
        Text(text = item.iata_code, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = modifier.width(10.dp))
        Text(text = item.name, fontSize = 14.sp)
    }
}
