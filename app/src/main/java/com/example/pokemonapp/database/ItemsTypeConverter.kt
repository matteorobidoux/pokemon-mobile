package com.example.pokemonapp.database

import androidx.room.TypeConverter
import com.example.pokemonapp.objects.Items
import com.example.pokemonapp.objects.PokemonCollection
import com.google.gson.Gson

class ItemsTypeConverter {

    @TypeConverter
    fun itemsToJson(value: Items) = Gson().toJson(value)

    @TypeConverter
    fun jsonToItems(value: String) = Gson().fromJson(value, Items::class.java)
}