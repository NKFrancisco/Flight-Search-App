/*
1. Nick Francisco
2. OSU
3. CS492
 */

package com.example.flightsearch

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.flightsearch.ui.navigation.FlightNavHost

/**
 * Top level composable that represents screens for the application.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightApp(navController: NavHostController = rememberNavController()) {

    Scaffold(
        topBar = {
            FlightTopAppBar(
                title = stringResource(R.string.app_name),
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
            )
        },
    ) { innerPadding ->

        FlightNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

/**
 * App bar to display title and conditionally display the back navigation.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(title) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}