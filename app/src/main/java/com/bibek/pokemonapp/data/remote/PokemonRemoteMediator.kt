package com.bibek.pokemonapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bibek.pokemonapp.BuildConfig
import com.bibek.pokemonapp.data.local.dao.PokemonDao
import com.bibek.pokemonapp.data.local.model.PokemonEntity
import com.bibek.pokemonapp.data.mapper.toPokemonEntity
import com.bibek.pokemonapp.data.remote.model.pokemon_list.res.PokemonListResponseDto
import com.bibek.pokemonapp.utils.PAGE_SIZE
import com.bibek.pokemonapp.utils.connectivity.ConnectionState
import com.bibek.pokemonapp.utils.connectivity.ConnectivityObserver
import com.bibek.pokemonapp.utils.network.collectResponse
import com.bibek.pokemonapp.utils.network.handleResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.first


class PokemonRemoteMediator(
    private val pokemonDao: PokemonDao,
    private val httpClient: HttpClient,
    private val connectivityObserver: ConnectivityObserver
) : PagingSource<Int, PokemonEntity>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonEntity>): Int? {
        return state.anchorPosition
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonEntity> {
        return try {
            val prev = params.key ?: 0
            val adjustedLoadSize =
                params.loadSize.coerceAtMost(PAGE_SIZE) // Modify loadSize if needed
            val deferredResult = CompletableDeferred<LoadResult<Int, PokemonEntity>>()
            if (connectivityObserver.currentConnectionState == ConnectionState.Available) {
                handleResponse<PokemonListResponseDto> {
                    httpClient.get(urlString = BuildConfig.BASE_URL) {
                        parameter("limit", PAGE_SIZE)
                        parameter("offset", prev)
                    }
                }.collectResponse(
                    onSuccess = { response ->
                        val pokemonList = response?.pokemonItems?.filterNotNull() ?: listOf()
                        if (prev == 0) {
                            pokemonDao.refreshPokemonList(pokemonList.map { it.toPokemonEntity() })
                        }
                        deferredResult.complete(
                            LoadResult.Page(
                                data = pokemonList.map {
                                    it.toPokemonEntity() },
                                prevKey = if (prev <= 0) null else prev - PAGE_SIZE,
                                nextKey = if (pokemonList.size < adjustedLoadSize) null else prev + PAGE_SIZE
                            )
                        )
                    }, onError = {
                        deferredResult.complete(
                            LoadResult.Page(
                                data = pokemonDao.getAllPokemon().first(),
                                prevKey = null,
                                nextKey = null
                            )
                        )
                    }, onLoading = {})
            } else {
                deferredResult.complete(
                    LoadResult.Page(
                        data = pokemonDao.getAllPokemon().first(),
                        prevKey = null,
                        nextKey = null
                    )
                )
            }
            return deferredResult.await()
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}