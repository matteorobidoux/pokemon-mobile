package com.example.pokemonapp.database


import androidx.room.TypeConverter
import com.example.pokemonapp.objects.Pokemon
import com.google.gson.Gson

class PokemonArrayListTypeConverter {

    @TypeConverter
    fun listToJson(value: ArrayList<Pokemon>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = ArrayList<Pokemon>(Gson().fromJson(value, Array<Pokemon>::class.java).toList())
}