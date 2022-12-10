package com.example.pokemonapp.database

import androidx.room.TypeConverter
import com.example.pokemonapp.objects.Move
import com.example.pokemonapp.objects.PokemonTeam
import com.google.gson.Gson

class PokemonTeamTypeConverter {

    @TypeConverter
    fun pokemonTeamToJson(value: PokemonTeam) = Gson().toJson(value)

    @TypeConverter
    fun jsonToPokemonTeam(value: String) = Gson().fromJson(value,PokemonTeam::class.java)
}