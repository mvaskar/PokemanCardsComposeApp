package com.pokemancards.app.di

import android.app.Application
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pokemancards.app.api.APIInterface
import com.pokemancards.app.helper.NetworkInterceptor
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

val json = Json {
    ignoreUnknownKeys = true
}
val contentType = "application/json".toMediaType()

@Module
internal class AppModule {

    @Singleton
    @Provides
    internal fun provideAPIInterface(retroFit: Retrofit): APIInterface {
        return retroFit.create(APIInterface::class.java)
    }

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.pokemontcg.io/v2/")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Singleton
    @Provides
    internal fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        networkInterceptor: NetworkInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addNetworkInterceptor { chain -> chain.proceed(chain.request()) }
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(networkInterceptor)
            .build()
    }

    @Singleton
    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    internal fun provideNetworkInterceptor(application: Application): NetworkInterceptor =
        NetworkInterceptor(application.applicationContext)
}