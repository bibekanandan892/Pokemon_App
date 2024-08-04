package com.bibek.pokemonapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pokemon_Table")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    val primaryKey:Int = 0,
    val id : String? = null,
    val name : String? = null,
)
