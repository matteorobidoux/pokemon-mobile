package com.example.pokemonapp.database

import androidx.room.TypeConverter
import com.example.pokemonapp.objects.PokemonCollection
import com.example.pokemonapp.objects.PokemonTeam
import com.google.gson.Gson

class PokemonCollectionTypeConverter {

    @TypeConverter
    fun pokemonCollectionToJson(value: PokemonCollection) = Gson().toJson(value)

    @TypeConverter
    fun jsonToPokemonCollection(value: String) = Gson().fromJson(value, PokemonCollection::class.java)
}