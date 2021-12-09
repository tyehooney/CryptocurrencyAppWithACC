package com.tyehooney.cryptocurrencies.domain.repository

import com.tyehooney.cryptocurrencies.data.remote.dto.CoinDetailDto
import com.tyehooney.cryptocurrencies.data.remote.dto.CoinDto

interface RemoteCoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}