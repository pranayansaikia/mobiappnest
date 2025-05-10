package com.mobiappnest.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mobiappnest.domain.model.Coin

@Composable
fun CoinItem(coin: Coin) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // Coin logo with Coil
            Image(
                painter = rememberAsyncImagePainter(coin.image),
                contentDescription = "Coin logo",
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.size(8.dp))

            Column {
                Text(
                    text = "${coin.name} (${coin.symbol})",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Price: $${coin.price}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "24h Change: ${coin.priceChange24h}%",
                    color = if (coin.priceChange24h >= 0) Color.Green else Color.Red
                )
            }
        }
    }
}