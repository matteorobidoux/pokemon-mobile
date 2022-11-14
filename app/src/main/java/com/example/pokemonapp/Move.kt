package com.example.pokemonapp

class Move(level_learned_at: Int, moveAccuracy: Int, moveAilment: String, moveAilmentChance: Int, moveCategory: String, moveDamageClass: String, moveHeal: Int, moveMaxPP: Int, moveName: String, movePower: Int, moveTarget: String, moveType: String) {
    val lvl_learned_at: Int
    val accuracy: Int
    val ailment: String
    val ailmentChance: Int
    val category: String
    val damageClass: String
    val heal: Int
    val maxPP: Int
    val name: String
    val power: Int
    val target: String
    val type: String

    init {
        lvl_learned_at = level_learned_at
        accuracy = moveAccuracy
        ailment = moveAilment
        ailmentChance = moveAilmentChance
        category = moveCategory
        damageClass = moveDamageClass
        heal = moveHeal
        maxPP = moveMaxPP
        name = moveName
        power = movePower
        target = moveTarget
        type = moveType
    }
}