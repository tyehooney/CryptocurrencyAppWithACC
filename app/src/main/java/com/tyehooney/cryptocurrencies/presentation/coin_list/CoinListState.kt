package com.tyehooney.cryptocurrencies.presentation.coin_list

import com.tyehooney.cryptocurrencies.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
