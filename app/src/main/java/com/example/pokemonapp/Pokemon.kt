package com.example.pokemonapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
class Pokemon(@PrimaryKey @ColumnInfo(name = "pokemonNumber") val pokemonNumber: String,
              @ColumnInfo(name="species") val species : String,
              @ColumnInfo(name="baseStateAttack") val baseStateAttack : Int,
              @ColumnInfo(name="baseStatDefense") val baseStatDefense : Int,
              @ColumnInfo(name="baseStatSpecialAttack") val baseStatSpecialAttack : Int,
              @ColumnInfo(name="baseStatSpecialDefense") val baseStatSpecialDefense: Int,
              @ColumnInfo(name="baseStateMaxHp") val baseStateMaxHp : Int,
              @ColumnInfo(name="baseStatSpeed") val baseStatSpeed : Int,
              @ColumnInfo(name="baseExperienceReward") val baseExperienceReward : Int,
              @ColumnInfo(name="types") val types : ArrayList<String>,
              @ColumnInfo(name="frontSprite") val frontSprite : String,
              @ColumnInfo(name="backSprite") val backSprite : String,
              @ColumnInfo(name="moves") val moves : ArrayList<Move>){

    init {

    }

}