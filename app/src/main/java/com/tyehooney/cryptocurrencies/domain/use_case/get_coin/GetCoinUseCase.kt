package com.tyehooney.cryptocurrencies.domain.use_case.get_coin

import com.tyehooney.cryptocurrencies.common.Constants.UNEXPECTED_ERROR_MSG
import com.tyehooney.cryptocurrencies.common.Resource
import com.tyehooney.cryptocurrencies.data.remote.dto.toCoinDetail
import com.tyehooney.cryptocurrencies.domain.model.CoinDetail
import com.tyehooney.cryptocurrencies.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: UNEXPECTED_ERROR_MSG))
        } catch (e: IOException) {
            emit(Resource.Error(UNEXPECTED_ERROR_MSG))
        }
    }
}