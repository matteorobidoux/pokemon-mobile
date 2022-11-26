package com.example.pokemonapp

import androidx.room.Entity


@Entity(tableName = "trainer_table")
class Trainer(trainerName: String) {
    lateinit var pokemonTeam: PokemonTeam
    lateinit var pokemonCollection: PokemonCollection

    @JvmName("setPokemonTeam1")
    fun setPokemonTeam(team: PokemonTeam){
        pokemonTeam = team
    }

    @JvmName("setPokemonCollection1")
    fun setPokemonCollection(collection: PokemonCollection){
        pokemonCollection = collection
    }
}