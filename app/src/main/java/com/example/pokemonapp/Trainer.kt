package com.example.pokemonapp

import androidx.room.Entity


@Entity(tableName = "trainer_table")
class Trainer(var trainerName: String) : java.io.Serializable{
    var pokemonTeam = PokemonTeam()
    var pokemonCollection = PokemonCollection()
    var items = Items()

    fun addPokemon(pokemon: Pokemon){
        if(pokemonTeam.pokemons.size < 6){
            pokemonTeam.pokemons.add(pokemon)
        } else {
            pokemonCollection.pokemons.add(pokemon)
        }
    }
}