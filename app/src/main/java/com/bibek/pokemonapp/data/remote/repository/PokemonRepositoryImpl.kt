package com.bibek.pokemonapp.data.remote.repository

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.bibek.pokemonapp.BuildConfig
import com.bibek.pokemonapp.data.local.dao.PokemonDao
import com.bibek.pokemonapp.data.mapper.toPokemon
import com.bibek.pokemonapp.data.remote.PokemonRemoteMediator
import com.bibek.pokemonapp.data.remote.model.pokemon_details.res.PokemonDetailsResponseDto
import com.bibek.pokemonapp.domain.model.pokemon_list.Pokemon
import com.bibek.pokemonapp.domain.repository.PokemonRepository
import com.bibek.pokemonapp.utils.connectivity.ConnectivityObserver
import com.bibek.pokemonapp.utils.network.NetworkResult
import com.bibek.pokemonapp.utils.network.handleResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val httpClient: HttpClient,
    private val connectivityObserver: ConnectivityObserver
) : PokemonRepository {
    override fun getPokemonList(
    ): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = 5,
            ),
            pagingSourceFactory = {
                PokemonRemoteMediator(
                    pokemonDao = pokemonDao,
                    httpClient = httpClient,
                    connectivityObserver = connectivityObserver
                )
            }
        ).flow.map { pagingData ->
            pagingData.map {
                it.toPokemon()
            }
        }

    }

    override fun getPokemonDetails(pokemonId: String): Flow<NetworkResult<PokemonDetailsResponseDto>> {
        return handleResponse {
            httpClient.get(urlString = BuildConfig.BASE_URL+"/$pokemonId")
        }
    }
}