package com.tyehooney.cryptocurrencies.domain.use_case.get_coins

import com.tyehooney.cryptocurrencies.common.Constants.UNEXPECTED_ERROR_MSG
import com.tyehooney.cryptocurrencies.common.Resource
import com.tyehooney.cryptocurrencies.data.remote.dto.toCoin
import com.tyehooney.cryptocurrencies.domain.model.Coin
import com.tyehooney.cryptocurrencies.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: UNEXPECTED_ERROR_MSG))
        } catch (e: IOException) {
            emit(Resource.Error(UNEXPECTED_ERROR_MSG))
        }
    }
}