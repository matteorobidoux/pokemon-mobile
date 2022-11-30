package com.example.pokemonapp

import androidx.room.Entity

@Entity(primaryKeys = ["pokemonNumber", "name"])
data class PokemonAndMoves(
    val pokemonNumber: String,
    val name: String
)