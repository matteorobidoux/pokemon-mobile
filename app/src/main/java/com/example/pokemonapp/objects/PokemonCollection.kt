package com.example.pokemonapp.objects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemoncollection_table")
class PokemonCollection() : java.io.Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var pokemons: ArrayList<Pokemon> = ArrayList()
}