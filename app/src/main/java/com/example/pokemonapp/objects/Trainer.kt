package com.example.pokemonapp.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "trainer_table")
class Trainer(
    @ColumnInfo(name="trainerName") val trainerName: String) : java.io.Serializable{
    @PrimaryKey var id: Int = 0
    var pokemonTeam = PokemonTeam()
    var pokemonCollection = PokemonCollection()
    var items = Items()
    var money = 1000

    fun addPokemon(pokemon: Pokemon){
        if(pokemonTeam.pokemons.size < 6){
            pokemonTeam.pokemons.add(pokemon)
        } else {
            pokemonCollection.pokemons.add(pokemon)
        }
    }
}