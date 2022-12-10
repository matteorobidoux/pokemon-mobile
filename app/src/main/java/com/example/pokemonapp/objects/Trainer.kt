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

    init {
        val pokeball: Items = Items("pokeball", 5, 200, "Regular PokeBall to catch pokemon", R.drawable.pokeball)
        val potion: Items = Items("potion", 5, 100, "Regular potion to heal pokemon by 20 hp", R.drawable.potion)
        items.add(pokeball)
        items.add(potion)
    }
}