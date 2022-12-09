package com.example.pokemonapp.database

import androidx.room.TypeConverter
import com.example.pokemonapp.objects.Move
import com.google.gson.Gson

class MoveArrayListTypeConverter {

    @TypeConverter
    fun listToJson(value: ArrayList<Move>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = ArrayList<Move>(Gson().fromJson(value, Array<Move>::class.java).toList())
}