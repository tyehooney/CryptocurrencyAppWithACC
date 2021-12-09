package com.tyehooney.cryptocurrencies.domain.use_case.get_coin

import com.tyehooney.cryptocurrencies.common.Constants.UNEXPECTED_ERROR_MSG
import com.tyehooney.cryptocurrencies.common.Resource
import com.tyehooney.cryptocurrencies.data.remote.dto.toCoinDetail
import com.tyehooney.cryptocurrencies.domain.model.CoinDetail
import com.tyehooney.cryptocurrencies.domain.repository.LocalCoinRepository
import com.tyehooney.cryptocurrencies.domain.repository.RemoteCoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val remoteRepository: RemoteCoinRepository,
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = remoteRepository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: UNEXPECTED_ERROR_MSG))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetail>(UNEXPECTED_ERROR_MSG))
        }
    }
}