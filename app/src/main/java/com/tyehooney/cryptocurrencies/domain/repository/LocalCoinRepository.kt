package com.tyehooney.cryptocurrencies.domain.repository

import coindb.CoinEntity
import com.tyehooney.cryptocurrencies.data.remote.dto.CoinDto

interface LocalCoinRepository {

    suspend fun getCoins(): List<CoinEntity>

    suspend fun saveCoin(coinDto: CoinDto)
}