package com.mobiappnest.domain.repository

import com.mobiappnest.domain.model.Coin
import com.mobiappnest.util.Resource

interface CoinRepository {
    suspend fun getTopCoins(): Resource<List<Coin>>
}
