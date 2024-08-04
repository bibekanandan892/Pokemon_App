package com.bibek.pokemonapp.data.local.database
import androidx.room.Database
import androidx.room.RoomDatabase
import com.bibek.pokemonapp.data.local.dao.PokemonDao
import com.bibek.pokemonapp.data.local.model.PokemonEntity
@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
abstract class  PokemonDatabase : RoomDatabase(){
    abstract fun pokemonDao(): PokemonDao
}