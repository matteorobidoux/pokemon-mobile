package com.example.pokemonapp.database

import androidx.room.TypeConverter
import com.example.pokemonapp.objects.Items
import com.example.pokemonapp.objects.Move
import com.google.gson.Gson

class ItemsArrayListTypeConverter {

    @TypeConverter
    fun listToJson(value: ArrayList<Items>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = ArrayList<Items>(Gson().fromJson(value, Array<Items>::class.java).toList())
}