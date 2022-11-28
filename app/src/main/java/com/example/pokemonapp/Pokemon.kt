package com.example.pokemonapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.math.floor
import kotlin.math.pow

@Entity(tableName = "pokemon_table")
class Pokemon(@PrimaryKey @ColumnInfo(name = "pokemonNumber") val pokemonNumber: String,
              @ColumnInfo(name="species") val species : String,
              @ColumnInfo(name="baseStateAttack") val pokemonBaseStateAttack : Int,
              @ColumnInfo(name="baseStatDefense") val pokemonBaseStatDefense : Int,
              @ColumnInfo(name="baseStatSpecialAttack") val pokemonBaseStatSpecialAttack : Int,
              @ColumnInfo(name="baseStatSpecialDefense") val pokemonBaseStatSpecialDefense: Int,
              @ColumnInfo(name="baseStateMaxHp") val pokemonBaseStateMaxHp : Int,
              @ColumnInfo(name="baseStatSpeed") val pokemonBaseStatSpeed : Int,
              @ColumnInfo(name="baseExperienceReward") val baseExperienceReward : Int,
              @ColumnInfo(name="types") val types : ArrayList<String>,
              @ColumnInfo(name="frontSprite") val frontSprite : String,
              @ColumnInfo(name="backSprite") val backSprite : String,
              @ColumnInfo(name="moves") val pokemonMoves : ArrayList<Move>){

    var name = species
    var level = floor(baseExperienceReward.toDouble().pow(1/3)).toInt()
    var baseStatAttack = pokemonBaseStateAttack * (50 + level)/50
    var baseStatDefense = pokemonBaseStatDefense * (50 + level)/50
    var baseStatSpecialAttack = pokemonBaseStatSpecialAttack * (50 + level)/50
    var baseStatSpecialDefense = pokemonBaseStatSpecialDefense * (50 + level)/50
    var baseStatSpeed = pokemonBaseStatSpeed * (50 + level)/50
    var baseStatMaxHp = pokemonBaseStateMaxHp * (50 + level)/50
    var currentHp = baseStatMaxHp
    var moves: ArrayList<Move> = ArrayList()

    init {
        for(move in pokemonMoves){
            if(moves.size < 4 && move.level_learned_at <= level){
                moves.add(move)
            }
        }
    }

}