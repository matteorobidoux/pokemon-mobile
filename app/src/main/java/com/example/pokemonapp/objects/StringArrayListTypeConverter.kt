package com.example.pokemonapp.objects

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringArrayListTypeConverter {

    @TypeConverter
    fun stringToArrayList(value: String) : ArrayList<String>{
        val listType = object : TypeToken<ArrayList<String>>(){}.type
        return Gson().fromJson(value, listType)
    }


    @TypeConverter
    fun arrayListToString(list: ArrayList<String>): String{
        return Gson().toJson(list)
    }
}