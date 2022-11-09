package com.example.pokemonapp

import android.content.Context
import java.io.IOException

class Utils {
    fun getJsonData(context: Context, fileName: String): String?{
        val jsonString: String
        try{
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch(ioExcpetion: IOException){
            ioExcpetion.printStackTrace()
            return null
        }
        return jsonString
    }
}