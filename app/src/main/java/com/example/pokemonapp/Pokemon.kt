package com.example.pokemonapp

data class Pokemon(val baseExperienceReward: Int, val baseStateAttack: Int, val baseStatDefense: Int, val baseStateMaxHp: Int, val baseStatSpecialAttack: Int, val baseStatSpecialDefense: Int, val baseStatSpeed: Int, val pokemonNumber: Int, val species: String, val types: ArrayList<String>, val moves: ArrayList<Move>)