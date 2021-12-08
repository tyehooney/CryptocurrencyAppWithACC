package com.tyehooney.cryptocurrencies.data.repository

import com.tyehooney.cryptocurrencies.data.remote.CoinPaprikaApi
import com.tyehooney.cryptocurrencies.data.remote.dto.CoinDetailDto
import com.tyehooney.cryptocurrencies.data.remote.dto.CoinDto
import com.tyehooney.cryptocurrencies.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}