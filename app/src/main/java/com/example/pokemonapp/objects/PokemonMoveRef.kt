package com.example.pokemonapp.objects

import androidx.room.Entity


@Entity(primaryKeys = ["pokemonNumber", "name"])
data class PokemonMoveRef(
    val pokemonNumber: String,
    val name: String
)