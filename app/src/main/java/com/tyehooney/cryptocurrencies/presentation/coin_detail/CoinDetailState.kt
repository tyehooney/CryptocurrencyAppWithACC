package com.tyehooney.cryptocurrencies.presentation.coin_detail

import com.tyehooney.cryptocurrencies.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
