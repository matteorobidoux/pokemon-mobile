package com.example.pokemonapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private val utils: Utils = Utils()

data class Move(val move: String, val level_learned_at: Int)