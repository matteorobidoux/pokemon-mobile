package com.example.pokemonapp.objects

import android.util.Log
import com.example.pokemonapp.R
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "trainer_table")
class Trainer(
    @ColumnInfo(name="trainerName") val trainerName: String) : java.io.Serializable{
    @PrimaryKey var id: Int = 0
    var pokemonTeam = PokemonTeam()
    var pokemonCollection = PokemonCollection()
    var items = ArrayList<Items>()
    var money = 1000

    fun addPokemon(pokemon: Pokemon){
        if(pokemonTeam.pokemons.size < 6){
            pokemonTeam.pokemons.add(pokemon)
        } else {
            pokemonCollection.pokemons.add(pokemon)
        }
    }

    init {
        val pokeball: Items = Items("pokeball", 5, 200, "Regular PokeBall to catch pokemon", R.drawable.pokeball)
        val potion: Items = Items("potion", 5, 100, "Regular potion to heal pokemon by 20 hp", R.drawable.potion)
        items.add(pokeball)
        items.add(potion)
    }
}