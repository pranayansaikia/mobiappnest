package com.mobiappnest.domain.usecase

import com.mobiappnest.domain.model.Coin
import com.mobiappnest.domain.repository.CoinRepository
import com.mobiappnest.util.Resource

class GetCoinsUseCase(private val repository: CoinRepository) {
    suspend operator fun invoke(): Resource<List<Coin>> {
        return repository.getTopCoins()
    }
}
