package com.bibek.pokemonapp.data.remote.model.pokemon_details.res


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailsResponseDto(
    @SerialName("abilities")
    val abilities: List<Ability>? = listOf(),
    @SerialName("base_experience")
    val baseExperience: Int? = 0,
    @SerialName("cries")
    val cries: Cries? = Cries(),
    @SerialName("forms")
    val forms: List<Form>? = listOf(),
    @SerialName("game_indices")
    val gameIndices: List<GameIndice>? = listOf(),
    @SerialName("height")
    val height: Double? = 0.0,
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("is_default")
    val isDefault: Boolean? = false,
    @SerialName("location_area_encounters")
    val locationAreaEncounters: String? = "",
    @SerialName("moves")
    val moves: List<Move>? = listOf(),
    @SerialName("name")
    val name: String? = "",
    @SerialName("order")
    val order: Int? = 0,
    @SerialName("species")
    val species: Species? = Species(),
    @SerialName("sprites")
    val sprites: Sprites? = Sprites(),
    @SerialName("stats")
    val stats: List<Stat>? = listOf(),
    @SerialName("types")
    val types: List<Type>? = listOf(),
    @SerialName("weight")
    val weight: Double? = 0.0
)