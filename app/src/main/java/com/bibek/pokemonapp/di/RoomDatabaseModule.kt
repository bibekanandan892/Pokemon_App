package com.bibek.pokemonapp.di

import android.content.Context
import androidx.room.Room
import com.bibek.pokemonapp.data.local.dao.PokemonDao
import com.bibek.pokemonapp.data.local.database.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomDatabaseModule {
    @Singleton
    @Provides
    fun provideRoomDataBase(@ApplicationContext context: Context): PokemonDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            PokemonDatabase::class.java,
            "POKEMON_DATABASE"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providePokemonDao(pokemonDatabase: PokemonDatabase): PokemonDao = pokemonDatabase.pokemonDao()
}