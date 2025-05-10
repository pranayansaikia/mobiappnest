package com.mobiappnest.domain.usecase

import com.mobiappnest.domain.model.Coin
import com.mobiappnest.domain.repository.CoinRepository

class GetCoinsUseCase(private val repository: CoinRepository) {
    suspend operator fun invoke(): List<Coin> = repository.getTopCoins()
}
