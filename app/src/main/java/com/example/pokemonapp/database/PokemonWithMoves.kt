package com.example.pokemonapp.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.pokemonapp.objects.Move
import com.example.pokemonapp.objects.Pokemon
import com.example.pokemonapp.objects.PokemonMoveRef

data class PokemonWithMoves (
    @Embedded val pokemon: Pokemon,
    @Relation(
        parentColumn = "pokemonNumber",
        entityColumn = "name",
        associateBy = Junction(PokemonMoveRef::class)
    )
    val moves : List<Move>
)