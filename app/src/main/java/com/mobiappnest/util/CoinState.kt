package com.mobiappnest.util


import com.mobiappnest.domain.model.Coin

data class CoinState(
    val coins: List<Coin> = emptyList(),
    val loading: Boolean = false
)