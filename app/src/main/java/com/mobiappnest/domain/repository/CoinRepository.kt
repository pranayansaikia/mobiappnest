package com.mobiappnest.domain.repository

import com.mobiappnest.domain.model.Coin

interface CoinRepository {
    suspend fun getTopCoins(): List<Coin>
}
