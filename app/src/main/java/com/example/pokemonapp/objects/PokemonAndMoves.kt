package com.example.pokemonapp.objects

import androidx.room.Entity

@Entity(primaryKeys = ["pokemonNumber", "name"])
data class PokemonAndMoves(
    val pokemonNumber: String,
    val name: String
)