package com.tyehooney.cryptocurrencies.data.di

import android.app.Application
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.tyehooney.cryptocurrencies.CoinDatabase
import com.tyehooney.cryptocurrencies.common.Constants.BASE_URL
import com.tyehooney.cryptocurrencies.data.local.LocalCoinRepositoryImpl
import com.tyehooney.cryptocurrencies.data.remote.CoinPaprikaApi
import com.tyehooney.cryptocurrencies.data.remote.RemoteCoinRepositoryImpl
import com.tyehooney.cryptocurrencies.domain.repository.LocalCoinRepository
import com.tyehooney.cryptocurrencies.domain.repository.RemoteCoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoinPaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return AndroidSqliteDriver(
            schema = CoinDatabase.Schema,
            context = app,
            name = "coin.db"
        )
    }

    @Provides
    @Singleton
    fun provideRemoteCoinRepository(api: CoinPaprikaApi): RemoteCoinRepository {
        return RemoteCoinRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideLocalCoinRepository(driver: SqlDriver): LocalCoinRepository {
        return LocalCoinRepositoryImpl(CoinDatabase(driver))
    }
}