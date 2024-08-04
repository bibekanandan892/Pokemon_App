package com.bibek.pokemonapp.data.mapper

import com.bibek.pokemonapp.data.local.model.PokemonEntity
import com.bibek.pokemonapp.data.remote.model.pokemon_list.res.PokemonItem

fun PokemonItem.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        name = name,
        id =extractLastNumberFromUrl(
            url
        )
    )
}

fun extractLastNumberFromUrl(url: String?): String? {
    return url?.trimEnd('/')?.substringAfterLast('/')
}
