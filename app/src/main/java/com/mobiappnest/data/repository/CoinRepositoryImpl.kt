package com.mobiappnest.data.repository

import com.mobiappnest.data.remote.CoinApiService
import com.mobiappnest.domain.model.Coin
import com.mobiappnest.domain.repository.CoinRepository

class CoinRepositoryImpl(private val api: CoinApiService) : CoinRepository {

    // Assuming api.getTopCoins() returns a list of CoinResponse (or similar) objects
    override suspend fun getTopCoins(): List<Coin> {
        return try {
            // If the response from API is a list of CoinResponse (you may need to adjust this based on your API)
            api.getTopCoins().map { apiCoin ->
                Coin(
                    id = apiCoin.id,
                    name = apiCoin.name,
                    symbol = apiCoin.symbol,
                    image = apiCoin.image,
                    price = apiCoin.price,
                    marketCap = apiCoin.marketCap,
                    rank = apiCoin.rank,
                    priceChange24h = apiCoin.priceChange24h,
                )
            }
        } catch (exception: Exception) {
            // Handle any errors that occur during the API call (e.g., network issues)
            emptyList<Coin>() // Return empty list in case of error
        }
    }
}