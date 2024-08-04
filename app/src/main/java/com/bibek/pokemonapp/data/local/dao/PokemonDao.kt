package com.bibek.pokemonapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.bibek.pokemonapp.data.local.model.PokemonEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface PokemonDao {
    @Transaction
    suspend fun refreshPokemonList( pokemonDtoList: List<PokemonEntity>) {
        deleteAll()
        upsertAll(pokemonDtoList)
    }

    @Upsert
    suspend fun upsertAll(pokemonEntities: List<PokemonEntity>)

    @Insert
    suspend fun insertPokemonList(pokemonEntities: List<PokemonEntity>)

    @Query("SELECT * FROM Pokemon_Table")
    fun getAllPokemon() : Flow<List<PokemonEntity>>

    @Query("SELECT * FROM Pokemon_Table")
    fun getPokemonPagingSource() : PagingSource<Int, PokemonEntity>

    @Query("DELETE FROM Pokemon_Table")
    suspend fun deleteAll()
}