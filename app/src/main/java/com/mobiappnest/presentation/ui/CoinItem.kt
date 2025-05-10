package com.mobiappnest.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mobiappnest.domain.model.Coin

@Composable
fun CoinItem(coin: Coin) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(modifier = Modifier.padding(16.dp)) {

            Image(
                painter = rememberAsyncImagePainter(coin.image),
                contentDescription = "${coin.name} logo",
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 16.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "${coin.name} (${coin.symbol.uppercase()})",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Price: $${String.format("%.2f", coin.current_price)}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(4.dp))  // Space between price and change


                Text(
                    text = "24h Change: ${
                        String.format(
                            "%.2f",
                            coin.price_change_percentage_24h
                        )
                    }%",
                    color = if (coin.price_change_percentage_24h >= 0) Color.Green else Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold  // Bold to make the change percentage more visible
                )
            }
        }
    }
}
