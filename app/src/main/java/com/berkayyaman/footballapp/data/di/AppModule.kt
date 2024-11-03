package com.berkayyaman.footballapp.data.di

import android.content.Context
import androidx.room.Room
import com.berkayyaman.footballapp.data.local.FootballDao
import com.berkayyaman.footballapp.data.local.FootballDatabase
import com.berkayyaman.footballapp.data.remote.FootballApi
import com.berkayyaman.footballapp.data.remote.mapper.DTOMapper
import com.berkayyaman.footballapp.data.repository.FootballRepository
import com.berkayyaman.footballapp.domain.repository.FootballRepositoryIMPL
import com.berkayyaman.footballapp.util.Constants
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by berkayyaman on 11,October,2024
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFootballApi(okHttpClient: OkHttpClient): FootballApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FootballApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(chuckerInterceptor: ChuckerInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context, chuckerCollector: ChuckerCollector): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            // The previously created Collector
            .collector(chuckerCollector)
            // The max body content length in bytes, after this responses will be truncated.
            .maxContentLength(250_000L)
            // List of headers to replace with ** in the Chucker UI
            .redactHeaders("Auth-Token", "Bearer")
            // Read the whole response body even when the client does not consume the response completely.
            // This is useful in case of parsing errors or when the response body
            // is closed before being read like in Retrofit with Void and Unit types.
            .build()

    }

    @Provides
    @Singleton
    fun provideChuckerCollector(@ApplicationContext context: Context): ChuckerCollector {
        return ChuckerCollector(
            context = context,
            // Toggles visibility of the notification
            showNotification = true,
            // Allows to customize the retention period of collected data
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
    }

    @Provides
    @Singleton
    fun provideMapper(): DTOMapper{
        return DTOMapper()
    }

    @Provides
    @Singleton
    fun provideFootballRepository(api: FootballApi,footballDao: FootballDao, dtoMapper: DTOMapper): FootballRepository {
        return FootballRepositoryIMPL(api,footballDao, dtoMapper)
    }

    @Provides
    @Singleton
    fun provideFootballDatabase(
        @ApplicationContext context: Context
    ): FootballDatabase{
        return Room.databaseBuilder(
            context = context,
            FootballDatabase::class.java,
            "Footbal_Database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFootballDao(footballDatabase: FootballDatabase): FootballDao{
        return footballDatabase.footballDao
    }

}