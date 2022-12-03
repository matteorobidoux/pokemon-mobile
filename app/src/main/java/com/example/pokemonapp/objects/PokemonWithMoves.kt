package com.example.pokemonapp.objects

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PokemonWithMoves(
    @Embedded
    var pokemon: Pokemon,

    @Relation(
        parentColumn = "pokemonNumber",
        entity = Move::class,
        entityColumn = "name",
        associateBy = Junction(
            value = PokemonAndMoves::class,
            parentColumn = "pokemonNumber",
            entityColumn = "name"
        )
    )

    var moves: List<Move>
)