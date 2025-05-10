package com.mobiappnest.data.remote


import com.mobiappnest.domain.model.Coin
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApiService {
    @GET("coins/markets")
    suspend fun getTopCoins(
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1
    ): List<Coin>  // ✅
}
