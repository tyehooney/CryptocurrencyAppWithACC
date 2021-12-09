package com.tyehooney.cryptocurrencies.data.local

import coindb.CoinEntity
import com.tyehooney.cryptocurrencies.domain.model.Coin

fun CoinEntity.toCoin(): Coin {
    return Coin(
        id = id,
        isActive = isActive == 1L,
        name = name ?: "",
        rank = rank?.toInt() ?: 0,
        symbol = symbol ?: ""
    )
}