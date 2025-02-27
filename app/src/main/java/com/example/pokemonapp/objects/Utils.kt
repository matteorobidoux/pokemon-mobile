package com.example.pokemonapp.objects

import com.example.pokemonapp.parseMoveData
import com.example.pokemonapp.parsePokemonData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class Utils {

     fun getPokemonJSON(data : String): JsonObject {

        val GSON: Gson = GsonBuilder().setPrettyPrinting().create()
        val url = URL("https://pokeapi.co/api/v2/pokemon/$data");
        val conn : HttpsURLConnection = url.openConnection() as HttpsURLConnection

        conn.requestMethod = "GET"
        conn.connect()

        val response = conn.inputStream.bufferedReader()
        val json : JsonObject = GSON.fromJson(response, JsonObject::class.java)

        return parsePokemonData(json)
    }

    fun getMoveJSON(data : String): JsonObject {

        val GSON: Gson = GsonBuilder().setPrettyPrinting().create()
        val url = URL("https://pokeapi.co/api/v2/move/$data");
        val conn : HttpsURLConnection = url.openConnection() as HttpsURLConnection

        conn.requestMethod = "GET"
        conn.connect()

        val response = conn.inputStream.bufferedReader()
        val json : JsonObject = GSON.fromJson(response, JsonObject::class.java)

        return parseMoveData(json)
    }
}