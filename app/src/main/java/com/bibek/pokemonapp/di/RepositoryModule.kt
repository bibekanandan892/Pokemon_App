package com.bibek.pokemonapp.di

import com.bibek.pokemonapp.data.local.dao.PokemonDao
import com.bibek.pokemonapp.data.remote.repository.PokemonRepositoryImpl
import com.bibek.pokemonapp.domain.repository.PokemonRepository
import com.bibek.pokemonapp.utils.connectivity.ConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providePokemonRepository(
        httpClient: HttpClient,
        connectivityObserver: ConnectivityObserver,
        pokemonDao: PokemonDao
    ): PokemonRepository {
        return PokemonRepositoryImpl(
            httpClient = httpClient,
            connectivityObserver = connectivityObserver,
            pokemonDao = pokemonDao
        )
    }
}