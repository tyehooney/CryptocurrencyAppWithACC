package com.tyehooney.cryptocurrencies.presentation

import com.tyehooney.cryptocurrencies.common.Constants.COIN_DETAIL_SCREEN
import com.tyehooney.cryptocurrencies.common.Constants.COIN_LIST_SCREEN

sealed class Screen(val route: String) {
    object CoinListScreen: Screen(COIN_LIST_SCREEN)
    object CoinDetailScreen: Screen(COIN_DETAIL_SCREEN)
}
