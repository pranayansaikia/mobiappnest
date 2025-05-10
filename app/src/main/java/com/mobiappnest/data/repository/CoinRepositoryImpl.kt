package com.mobiappnest.data.repository

import android.util.Log
import com.mobiappnest.data.remote.CoinApiService
import com.mobiappnest.domain.model.Coin
import com.mobiappnest.domain.repository.CoinRepository
import com.mobiappnest.util.Resource

class CoinRepositoryImpl(private val api: CoinApiService) : CoinRepository {

    override suspend fun getTopCoins(): Resource<List<Coin>> {
        return try {
            val coinResponses = api.getTopCoins()

            Log.d("CoinRepository", "API Response: $coinResponses")

            val coins = coinResponses.map { apiCoin ->
                Coin(
                    id = apiCoin.id,
                    symbol = apiCoin.symbol,
                    name = apiCoin.name,
                    image = apiCoin.image ?: "https://example.com/placeholder.png", // fallback if image is null
                    current_price = apiCoin.current_price,
                    market_cap = apiCoin.market_cap,
                    market_cap_rank = apiCoin.market_cap_rank,
                    fully_diluted_valuation = apiCoin.fully_diluted_valuation,
                    total_volume = apiCoin.total_volume,
                    high_24h = apiCoin.high_24h,
                    low_24h = apiCoin.low_24h,
                    price_change_24h = apiCoin.price_change_24h,
                    price_change_percentage_24h = apiCoin.price_change_percentage_24h,
                    market_cap_change_24h = apiCoin.market_cap_change_24h,
                    market_cap_change_percentage_24h = apiCoin.market_cap_change_percentage_24h,
                    circulating_supply = apiCoin.circulating_supply,
                    total_supply = apiCoin.total_supply,
                    max_supply = apiCoin.max_supply,
                    ath = apiCoin.ath,
                    ath_change_percentage = apiCoin.ath_change_percentage,
                    ath_date = apiCoin.ath_date,
                    atl = apiCoin.atl,
                    atl_change_percentage = apiCoin.atl_change_percentage,
                    atl_date = apiCoin.atl_date,
                    roi = apiCoin.roi,
                    last_updated = apiCoin.last_updated
                )
            }

            Resource.Success(coins)
        } catch (exception: Exception) {
            Log.e("CoinRepository", "Error fetching top coins", exception)
            Resource.Error("Failed to load top coins: ${exception.localizedMessage}")
        }
    }
}

