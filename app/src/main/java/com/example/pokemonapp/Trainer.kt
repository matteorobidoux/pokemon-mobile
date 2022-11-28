package com.example.pokemonapp

import androidx.room.Entity


@Entity(tableName = "trainer_table")
class Trainer(var trainerName: String) {
    lateinit var pokemonTeam: PokemonTeam
    lateinit var pokemonCollection: PokemonCollection
    var items = Items()

    @JvmName("setPokemonTeam1")
    fun setPokemonTeam(team: PokemonTeam){
        pokemonTeam = team
    }

    @JvmName("setPokemonCollection1")
    fun setPokemonCollection(collection: PokemonCollection){
        pokemonCollection = collection
    }

    fun addPokemon(pokemon: Pokemon){
        if(pokemonTeam.pokemons.size < 6){
            pokemonTeam.pokemons.add(pokemon)
        } else {
            pokemonCollection.pokemons.add(pokemon)
        }
    }
}