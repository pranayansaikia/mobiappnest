package com.mobiappnest.presentation.ui

import CoinViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items

@Composable
fun CoinScreen(viewModel: CoinViewModel) {
    val state = viewModel.state.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(searchQuery) {
        viewModel.filterCoins(searchQuery)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // Search Bar
        SearchBar(searchQuery) { newQuery -> searchQuery = newQuery }

        Spacer(modifier = Modifier.height(16.dp))

        // Loading Indicator
        if (state.value.loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            // LazyColumn for scrollable coin list
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                // Ensure you import 'items' for LazyColumn
                items(state.value.coins) { coin ->
                    CoinItem(coin)
                }
            }
        }
    }
}
