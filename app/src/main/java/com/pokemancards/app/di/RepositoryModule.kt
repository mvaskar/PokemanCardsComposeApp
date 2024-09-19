package com.pokemancards.app.di

import com.pokemancards.app.api.APIInterface
import com.pokemancards.app.api.PokemanCardRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class RepositoryModule {

    @Singleton
    @Provides
    internal fun providePokemanCardRepository(
        apiInterface: APIInterface,
    ): PokemanCardRepository =
        PokemanCardRepository(apiInterface)
}
