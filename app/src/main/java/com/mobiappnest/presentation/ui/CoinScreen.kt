package com.mobiappnest.presentation.ui

import CoinViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mobiappnest.util.isNetworkAvailable

@Composable
fun CoinScreen(viewModel: CoinViewModel) {
    val context = LocalContext.current
    val state = viewModel.state.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var hasNetwork by remember { mutableStateOf(isNetworkAvailable(context)) }

    LaunchedEffect(searchQuery) {
        viewModel.filterCoins(searchQuery)
    }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.value.loading)

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        SearchBar(searchQuery) { newQuery -> searchQuery = newQuery }

        Spacer(modifier = Modifier.height(16.dp))

        if (!hasNetwork) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "No internet connection.\nPlease check your network.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    hasNetwork = isNetworkAvailable(context)
                    if (hasNetwork) {
                        viewModel.refreshCoins()
                    }
                }) {
                    Text("Retry")
                }
            }
            return@Column
        }

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                hasNetwork = isNetworkAvailable(context)
                if (hasNetwork) {
                    viewModel.refreshCoins()
                }
            }
        ) {
            if (state.value.loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            } else {
                if (state.value.coins.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "No coins available at the moment.",
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 16.dp)
                    ) {
                        items(state.value.coins) { coin ->
                            CoinItem(coin)
                        }
                    }
                }
            }

            state.value.error?.let {
                Text(
                    text = "Error: $it",
                    color = Color.Red,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp)
                )
            }
        }
    }
}
