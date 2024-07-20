/*
1. Nick Francisco
2. OSU
3. CS492
 */

package com.example.flightsearch.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.data.Airport
import kotlinx.coroutines.launch


@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    departingAirport: Airport,
    viewModel: SearchViewModel = viewModel(factory = SearchViewModel.factory),
) {
    // List of flight routes
    val routes by viewModel.getFlightRoutes(departingAirport.iata_code).collectAsState(emptyList())

    SearchBody(
        departingAirportCode = departingAirport.iata_code,
        departingAirportName = departingAirport.name,
        routeList = routes,
        viewModel = viewModel,
        modifier = modifier
            .fillMaxSize()
    )
}

@Composable
fun SearchBody(
    departingAirportCode: String,
    departingAirportName: String,
    routeList: List<Airport>,
    viewModel: SearchViewModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.padding(start = 10.dp, top = 10.dp)) {
            Text(text = "Flights from ",
                fontWeight = FontWeight.Bold,
            )
            Text(departingAirportCode,
                fontWeight = FontWeight.Bold,
            )
        }
        RouteList(
            departingAirportCode = departingAirportCode,
            departingAirportName = departingAirportName,
            viewModel = viewModel,
            routeList = routeList,
            modifier = Modifier
        )
    }
}

@Composable
fun RouteList(
    departingAirportCode: String,
    departingAirportName: String,
    routeList: List<Airport>,
    viewModel: SearchViewModel,
    modifier: Modifier
){
    LazyColumn(modifier = modifier) {
        items(items = routeList, key = { airport -> airport.id }) { item->
            RouteCard(
                item = item,
                departingAirportCode = departingAirportCode,
                departingAirportName = departingAirportName,
                viewModel = viewModel,
                modifier = modifier
            )
        }
    }
}

@Composable
fun RouteCard(
    item: Airport,
    departingAirportCode: String,
    departingAirportName: String,
    viewModel: SearchViewModel,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    Card(modifier = modifier
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
                    Text(text = departingAirportCode)
                    Spacer(modifier = modifier.width(10.dp))
                    Text(text = departingAirportName)
                }
                Row {
                    Text(text = "Arrive")
                }
                Row {
                    Text(text = item.iata_code)
                    Spacer(modifier = modifier.width(10.dp))
                    Text(text = item.name)
                }
            }
            // Favorite Icon
            IconButton(
                onClick = {
                    coroutineScope.launch {

                        viewModel.saveFavorite(departingAirportCode, item.iata_code) }
                          },
                modifier = modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Favorite Icon"
                )
            }
        }
    }
}
