package com.mobiappnest.domain.model


data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val image: String,    // URL to the coin's logo
    val price: Double,
    val marketCap: Long,
    val rank: Int,
    val priceChange24h: Double
)
