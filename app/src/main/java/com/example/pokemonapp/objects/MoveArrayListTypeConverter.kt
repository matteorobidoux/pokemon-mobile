package com.example.pokemonapp.objects

import androidx.room.TypeConverter
import com.google.gson.Gson

class MoveArrayListTypeConverter {

    @TypeConverter
    fun listToJson(value: ArrayList<Move>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = ArrayList<Move>(Gson().fromJson(value, Array<Move>::class.java).toList())
}