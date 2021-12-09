package com.tyehooney.cryptocurrencies.data.remote

import com.tyehooney.cryptocurrencies.data.remote.dto.CoinDetailDto
import com.tyehooney.cryptocurrencies.data.remote.dto.CoinDto
import com.tyehooney.cryptocurrencies.domain.repository.RemoteCoinRepository
import javax.inject.Inject

class RemoteCoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : RemoteCoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}