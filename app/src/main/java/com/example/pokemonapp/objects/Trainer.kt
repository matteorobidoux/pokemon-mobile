package com.example.pokemonapp.objects

import android.util.Log
import androidx.room.Entity


@Entity(tableName = "trainer_table")
class Trainer(var trainerName: String) : java.io.Serializable{
    var pokemonTeam = PokemonTeam()
    var pokemonCollection = PokemonCollection()
    var items = Items()
    var money = 1000

    fun addPokemon(pokemon: Pokemon){
        Log.d("STARTER", "adding ${pokemon.name}")
        if(pokemonTeam.pokemons.size < 6){
            Log.d("STARTER", "less than 6")
            pokemonTeam.pokemons.add(pokemon)
            Log.d("STARTER", "pokemon: ${pokemonTeam.pokemons.size}")
        } else {
            Log.d("STARTER", "more than 6")
            pokemonCollection.pokemons.add(pokemon)
        }
    }
}