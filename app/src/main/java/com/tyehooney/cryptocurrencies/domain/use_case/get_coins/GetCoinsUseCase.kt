package com.tyehooney.cryptocurrencies.domain.use_case.get_coins

import com.tyehooney.cryptocurrencies.common.Constants.UNEXPECTED_ERROR_MSG
import com.tyehooney.cryptocurrencies.common.Resource
import com.tyehooney.cryptocurrencies.data.local.toCoin
import com.tyehooney.cryptocurrencies.data.remote.dto.CoinDto
import com.tyehooney.cryptocurrencies.data.remote.dto.toCoin
import com.tyehooney.cryptocurrencies.domain.model.Coin
import com.tyehooney.cryptocurrencies.domain.repository.LocalCoinRepository
import com.tyehooney.cryptocurrencies.domain.repository.RemoteCoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val remoteRepository: RemoteCoinRepository,
    private val localRepository: LocalCoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = remoteRepository.getCoins()
            emit(Resource.Success<List<Coin>>(coins.map { it.toCoin() }))
            coins.forEach { coin -> saveCoin(coin) }
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: UNEXPECTED_ERROR_MSG))
        } catch (e: IOException) {
            when (e) {
                is UnknownHostException -> emit(getCoinsFromDB())
                else -> emit(Resource.Error<List<Coin>>(UNEXPECTED_ERROR_MSG))
            }
        }
    }

    private suspend fun getCoinsFromDB(): Resource<List<Coin>> {
        return try {
            val coins = localRepository.getCoins().map { it.toCoin() }
            if (coins.isNullOrEmpty()) Resource.Error<List<Coin>>(UNEXPECTED_ERROR_MSG)
            else Resource.Success<List<Coin>>(coins)
        } catch (e: Exception) {
            Resource.Error<List<Coin>>(UNEXPECTED_ERROR_MSG)
        }
    }

    private suspend fun saveCoin(coinDto: CoinDto) {
        localRepository.saveCoin(coinDto)
    }
}