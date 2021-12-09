package com.tyehooney.cryptocurrencies.data.local

import coindb.CoinEntity
import com.tyehooney.cryptocurrencies.CoinDatabase
import com.tyehooney.cryptocurrencies.data.remote.dto.CoinDto
import com.tyehooney.cryptocurrencies.domain.repository.LocalCoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalCoinRepositoryImpl(
    db: CoinDatabase
) : LocalCoinRepository {

    private val queries = db.coinEntityQueries
    override suspend fun getCoins(): List<CoinEntity> =
        withContext(Dispatchers.IO) {
            queries.getCoins().executeAsList()
        }

    override suspend fun saveCoin(coinDto: CoinDto) {
        withContext(Dispatchers.IO) {
            coinDto.apply {
                queries.saveCoin(
                    id = id,
                    isActive = if (isActive) 1 else 0,
                    isNew = if (isNew) 1 else 0,
                    name = name,
                    rank = rank.toLong(),
                    symbol = symbol,
                    type = type
                )
            }
        }
    }
}