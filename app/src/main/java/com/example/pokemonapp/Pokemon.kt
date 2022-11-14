package com.example.pokemonapp

class Pokemon(pokemonBaseExperienceReward: Int, pokemonBaseStateAttack: Int, pokemonBaseStatDefense: Int, pokemonBaseStateMaxHp: Int, pokemonBaseStatSpecialAttack: Int, pokemonBaseStatSpecialDefense: Int, pokemonBaseStatSpeed: Int, number: Int, pokemonSpecies: String, pokemonTypes: ArrayList<String>, pokemonMoves: ArrayList<Move>){
    val baseExperienceReward : Int
    val baseStateAttack : Int
    val baseStatDefense : Int
    val baseStateMaxHp : Int
    val baseStatSpecialAttack : Int
    val baseStatSpecialDefense : Int
    val baseStatSpeed : Int
    val pokemonNumber : Int
    val species : String
    val types : ArrayList<String>
    val moves : ArrayList<Move>

    init {
        baseExperienceReward = pokemonBaseExperienceReward
        baseStateAttack = pokemonBaseStateAttack
        baseStatDefense = pokemonBaseStatDefense
        baseStateMaxHp = pokemonBaseStateMaxHp
        baseStatSpecialAttack = pokemonBaseStatSpecialAttack
        baseStatSpecialDefense = pokemonBaseStatSpecialDefense
        baseStatSpeed = pokemonBaseStatSpeed
        pokemonNumber = number
        species = pokemonSpecies
        types = pokemonTypes
        moves = pokemonMoves
    }

    override fun toString(): String {
        return "$baseExperienceReward $baseStateAttack $baseStatDefense $baseStateMaxHp $baseStatSpecialAttack $baseStatSpecialDefense $baseStatSpeed $pokemonNumber $species $types $moves"
    }
}