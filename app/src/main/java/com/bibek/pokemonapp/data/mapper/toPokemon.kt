package com.bibek.pokemonapp.data.mapper

import com.bibek.pokemonapp.data.local.model.PokemonEntity
import com.bibek.pokemonapp.domain.model.pokemon_list.Pokemon

fun PokemonEntity.toPokemon(): Pokemon {
    return Pokemon(
        name = name?:"",
        id =id?:"",
        image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    )
}
